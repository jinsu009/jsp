package kr.or.ddit.commons.service;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.vo.MemberVO;
//2020-06-11
@Service
public class AuthenticateServiceImpl implements IAuthenticateService {
	// 싱글톤 패턴
//	IMemberDAO dao =MemberDAOImpl.getInstance();
//	IMemberDAO dao = new MemberDAOImpl();
	
	@Inject
	IMemberDAO dao;
		
	@Override
	public ServiceResult authenticated(MemberVO member) {
		MemberVO savedMember = dao.selectMember(member.getMem_id());
		ServiceResult result = null;
		if(savedMember!=null) {
			// id 와 패스워드  비교 
			if(member.getMem_pass().equals(savedMember.getMem_pass())){
				BeanUtils.copyProperties(savedMember,member);
				// member와 savedMember가 서로 형태가 다른 객체 일 경우 발생할 예외처리 -- 개발자 잘못 : 500 상태코드발생
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}
}
