package kr.or.ddit.commons.service;

import kr.or.ddit.commons.dao.IAuthenticateDAO;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.commons.dao.AuthenticateDAOImpl;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.MemberVO;

// 2020-05-19
public class AuthenticateServiceImpl implements IAuthenticateService {
	// 싱글톤 패턴
	IAuthenticateDAO dao = AuthenticateDAOImpl.getInstance();
	private AuthenticateServiceImpl() {}
	private static IAuthenticateService self;
	public static IAuthenticateService getInstance() {
		if(self==null) self = new AuthenticateServiceImpl();
		return self;
	}
	@Override
	public ServiceResult authenticated(MemberVO member) {
		MemberVO savedMember = dao.selectMember(member);
		ServiceResult result = null;
		// 쿼리문에서 is null 부분을 빼고 조건을 추가 
		// 탈퇴한 회원이 다시 로그인을 하지 못하도록 한다.
		if(savedMember!=null && !"Y".equals(savedMember.getMem_delete())) {
			try {
				//???
				BeanUtils.copyProperties(member, savedMember);
				// member와 savedMember가 서로 형태가 다른 객체 일 경우 발생할 예외처리 -- 개발자 잘못 : 500 상태코드발생
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
			// null > 존재하지 않음 
		}
		return result;
	}
}
