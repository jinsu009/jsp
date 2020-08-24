package kr.or.ddit.commons.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

// 2020-05-19
public class AuthenticateServiceImpl implements IAuthenticateService {
	// 싱글톤 패턴
	IMemberDAO dao =MemberDAOImpl.getInstance();
	private AuthenticateServiceImpl() {}
	private static IAuthenticateService self;
	public static IAuthenticateService getInstance() {
		if(self==null) self = new AuthenticateServiceImpl();
		return self;
	}
	@Override
	public ServiceResult authenticated(MemberVO member) {
		MemberVO savedMember = dao.selectMember(member.getMem_id());
		ServiceResult result = null;
		if(savedMember!=null) {
			// id 와 패스워드  비교 
			if(member.getMem_pass().equals(savedMember.getMem_pass())){
				try {
					BeanUtils.copyProperties(member, savedMember);
					// member와 savedMember가 서로 형태가 다른 객체 일 경우 발생할 예외처리 -- 개발자 잘못 : 500 상태코드발생
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
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
