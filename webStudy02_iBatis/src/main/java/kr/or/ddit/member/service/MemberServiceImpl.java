package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

// 2020-05-25
public class MemberServiceImpl implements IMemberService {
	
	IMemberDAO dao = MemberDAOImpl.getInstance();

	private MemberServiceImpl() {}
	private static MemberServiceImpl self;
	public static IMemberService getInstance() {
		if(self==null) self = new MemberServiceImpl();
		return self;
	}
	
	@Override
	public ServiceResult createMember(MemberVO memvo) {
		
		ServiceResult result = null;
	
			int cnt = dao.insertMember(memvo);
			if(cnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		
//		try {
//			readMember(memvo.getMem_id());
//			result = ServiceResult.PKDUPLICATED;
//		}catch(DataNotFoundException e) {
//			int cnt = dao.insertMember(memvo);
//			if(cnt >0) {
//				result = ServiceResult.OK;
//			}else {
//				result = ServiceResult.FAIL;
//			}
//		}
		return result;
	}

	@Override
	public MemberVO readMember(String mem_id) {
		MemberVO memvo = dao.selectMember(mem_id);
		if(memvo==null) {
			memvo = null;
		}
		return memvo;
	}

	@Override
	public int readMemberCount(PagingVO pagingVO) {
		return dao.selectMemberCount(pagingVO);
	}

	@Override
	public List<MemberVO> readMemberList(PagingVO pagingVO) {
		return dao.selectMemberList(pagingVO);
	}

	@Override
	
	public ServiceResult modifyMember(MemberVO memvo) {
		ServiceResult result = null;
		MemberVO savedMember = readMember(memvo.getMem_id());
		
		if(savedMember.getMem_pass().equals(memvo.getMem_pass())) {
			int cnt = dao.updateMember(memvo);
			if(cnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			// 비밀번호가 일치 하지 않을 경우 
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO memvo) {
		
		ServiceResult result = null;
		MemberVO savedMember = readMember(memvo.getMem_id());
		if(savedMember.getMem_pass().equals(memvo.getMem_pass())) {
			int cnt = dao.deleteMember(memvo.getMem_id());
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

}
