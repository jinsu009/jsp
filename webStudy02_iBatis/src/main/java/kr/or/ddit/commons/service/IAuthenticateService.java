package kr.or.ddit.commons.service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.MemberVO;
/**
 * 인증 처리에 사용할 로직을 가진 Business Logic Layer(Service Layer)
 * 
 *
 */
// 2020-05-19
public interface IAuthenticateService {

	/**
	 * 인증 로직 
	 * @param member
	 * @return 존재여부 확인(NOTEXIST), 비번을 통한 인증(OK, INVALIDPASSWORD) 
	 */
	public ServiceResult authenticated(MemberVO member);
}
