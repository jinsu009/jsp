package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardService {

	/**
	 * 게시글 등록
	 * @param boardVO
	 * @return
	 */
	public ServiceResult createBoard(BoardVO boardVO);
	
	/**
	 * 조건에 해당하는 게시글 갯수 반환
	 * @param pagingVO
	 * @return
	 */
	public int readBoardCount(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 게시글 전체 목록 조회
	 * @param pagingVO
	 * @return
	 */
	public List<BoardVO> readBoardList(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 게시글 상세 조회
	 * @param bo_no
	 * @return
	 */
	public BoardVO readBoard(int bo_no);
	 
	/**
	 * 게시글 수정
	 * @param bo_no
	 * @return
	 */
	public ServiceResult modifyBoard(BoardVO board);
	
	/**
	 * 게시글 삭제 
	 * @param bo_no
	 * @return
	 */
	public ServiceResult removeBoard(BoardVO boardVO);
	
	/**
	 * 게시글 첨부파일 다운로드 
	 * @param bo_no
	 * @return
	 */
	public AttatchVO downloadAttatch(int bo_no);
}
