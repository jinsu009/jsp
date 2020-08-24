package kr.or.ddit.commons.dao;

import kr.or.ddit.vo.MemberVO;

/**
 * 인증처리에 사용할 Persistence Layer
 */
// 2020-05-19
public interface IAuthenticateDAO {
	/**
	 * 식별자를 이용한 사용정보 조회 
	 * @param member TODO
	 * @return 존재한다면 vo 객체 반환, 존재하지 않으면 null 반환
	 */
	public MemberVO selectMember(MemberVO member);

}

