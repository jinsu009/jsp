package kr.or.ddit.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.dao.IAttatchDAO;
import kr.or.ddit.dao.IBoardDAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class BoardServiceImpl implements IBoardService{

	@Inject
	IBoardDAO boardDao;
	@Inject
	IAttatchDAO attDao;
	
	@Inject
	WebApplicationContext context;
	@Value("#{appInfo.attatchPath}")
	String attatchPath;
	File saveFolder;
	
	@PostConstruct
	public void init() {
		String realPath = context.getServletContext().getRealPath(attatchPath);
		saveFolder = new File(realPath);
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	
	private int processAttatches(BoardVO boardVO) {
		// -------------------- 저장된 첨부파일 지우기 
		int[] delNos = boardVO.getDeleteAttatches();
		int rowcnt = 0;
		String[] delAttSaveNames = null;
		if(delNos!=null && delNos.length>0) {
			delAttSaveNames = new String[delNos.length];
			for(int i=0; i<delNos.length; i++) {
				delAttSaveNames[i] = attDao.selectAttatch(delNos[i]).getAtt_savename();
			}
			rowcnt = attDao.deleteAttatchs(boardVO); // meta data 삭제 
			deleteBinary(delAttSaveNames); 
		}
		// 쿼리문 : attatch 업데이트 > attatch 삭제
		
		// --------------------- 첨부파일이 있다면 새롭게 등록되는 첨부파일 업로드
		List<AttatchVO> attatchList = boardVO.getAttatchList();
		if(attatchList != null && !attatchList.isEmpty()) {
			rowcnt = attDao.insertAttatchs(boardVO);
			try {
			for(AttatchVO attatch : attatchList) {
				attatch.getRealFile().transferTo(new File(saveFolder, attatch.getAtt_savename()));
			}
			}catch(IllegalStateException|IOException e) {
				throw new RuntimeException(e);
			}
		}
		deleteBinary(delAttSaveNames);
		return rowcnt;
	}
	
	@Transactional  // 두개의 메소드 중 하나의 기능이라도 터지면 알아서 rollback 시켜줌 
	@Override
	public ServiceResult createBoard(BoardVO boardVO) {
		// 새로운 첨부파일 업로드
		// createprod랑 똑같음 
		int rowcnt = boardDao.insertBoard(boardVO);
		rowcnt += processAttatches(boardVO);
		ServiceResult result = null;
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public int readBoardCount(PagingVO<BoardVO> pagingVO) {
		return boardDao.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> readBoardList(PagingVO<BoardVO> pagingVO) {
		return boardDao.selectBoardList(pagingVO);
	}

	@Override
	public BoardVO readBoard(int bo_no) {
		// null 경우와 조회수 증가시키기 
		BoardVO board = boardDao.selectBoard(bo_no);
		if(board == null) {
			throw new DataNotFoundException(bo_no+" 에 해당하는 글이 없다. ");
		}
		boardDao.incrementHit(bo_no);
		return board;
	}

	@Override
	public ServiceResult modifyBoard(BoardVO boardvo) {
		// 기존의 첨부파일 삭제 , 새로운 첨부파일 업로드 
		BoardVO saveBoard = readBoard(boardvo.getBo_no());
		ServiceResult result = null;
		// 인증 : 비밀번호가 다르면 수정할 수 없다. 
		if(saveBoard.getBo_pass().equals(boardvo.getBo_pass())) {
			int rowcnt = boardDao.updateBoard(boardvo);
			if(rowcnt>0) {
				processAttatches(boardvo);
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}		
		return result;
	}
	
	private void deleteBinary(String[] delAttSaveNames) {
		if(delAttSaveNames==null || delAttSaveNames.length==0) {
			// 지울데이터가없으면
			return;
		}
		try {
			// 이진 파일 삭제 
			for(String delName : delAttSaveNames) {
				FileUtils.forceDelete(new File(saveFolder, delName));
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Transactional
	@Override
	public ServiceResult removeBoard(BoardVO boardVO) {
		// 삭제 , 첨부파일도같이 지워야한다.
		// 부모글이 지워지면 자식글도 같이 지워지도록 해야하나 ? 
		// -- 선생님 : 부모글을 지우면 자식글도 같이 지워지게 함 
		ServiceResult result = null;
		BoardVO saveBoard = readBoard(boardVO.getBo_no());
		if(saveBoard.getBo_pass().equals(boardVO.getBo_pass())) {
			
			// 첨부파일을 삭제 하기위해 이진데이터가 어디에 저장되어 있는지 알아야한다. 
			List<AttatchVO> attList = saveBoard.getAttatchList();
			String[] delAttNames = null;
			if(attList!=null && !attList.isEmpty()) {
				delAttNames = new String[attList.size()];
				for(int i=0; i<delAttNames.length; i++) {
					delAttNames[i] = attList.get(i).getAtt_savename();
				}
			}
			int cnt = boardDao.deleteBoard(boardVO.getBo_no());
			if(cnt>0) {
				deleteBinary(delAttNames); // 이진데이터 삭제	 // cascade 설정 변경으로 메타데이터도 같이 삭제 
			 	result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	
	@Override
	public AttatchVO downloadAttatch(int att_no) {
		AttatchVO attatch = attDao.selectAttatch(att_no);
		if(attatch == null) {
			throw new DataNotFoundException(att_no+"에 해당하는 파일이 없음");
		}
		attDao.incrementDownCount(att_no);
		return null;
	}

}
