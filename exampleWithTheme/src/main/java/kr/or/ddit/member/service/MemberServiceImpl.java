package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberServiceImpl implements IMemberService {
	private static MemberServiceImpl self;
	private MemberServiceImpl() {}
	public static MemberServiceImpl getInstance() {
		if(self==null) self = new MemberServiceImpl();
		return self;
	}
	
	IMemberDAO dao = MemberDAOImpl.getInstance();
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		try {
			readMember(member.getMem_id());
			result = ServiceResult.PKDUPLICATED;
		}catch (DataNotFoundException e) {
			int cnt = dao.insertMember(member);
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}
		return result;
	}

	@Override
	public MemberVO readMember(String mem_id) {
		MemberVO member = dao.selectMember(mem_id);
		if(member==null) throw new DataNotFoundException(mem_id+"는 없는 회원임.");
		return member;
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
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult result = null;
		MemberVO savedMember = readMember(member.getMem_id());
		if(savedMember.getMem_pass().equals(member.getMem_pass())) {
			int cnt = dao.updateMember(member);
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

	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result = null;
		MemberVO savedMember = readMember(member.getMem_id());
		if(savedMember.getMem_pass().equals(member.getMem_pass())) {
			int cnt = dao.deleteMember(member.getMem_id());
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
