package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 
 * 회원관리를(CRUD) 위한 Business Logic Layer
 *
 */

// 2020-05-25
public interface IMemberService {

	/**
	 * 신규회원 등록 
	 * @param memvo
	 * @return PKDUPLICATED(중복), OK, FAIL
	 */
	public ServiceResult createMember(MemberVO memvo);
	
	/**
	 * 회원 정보 상세 조회
	 * @param mem_id
	 * @return 존재하지 않는 회원이라면 DataNotFoundException 발생 
	 */
	public MemberVO readMember(String mem_id);
	
	/**
	 * 검색 조건에 맞는 등록 회원수 
	 * @return
	 */
	public int readMemberCount(PagingVO pagingVO);
	
	/**
	 * 검색 조건에 맞는 회원 목록
	 * @param pagingVO TODO
	 */
	public List<MemberVO> readMemberList(PagingVO pagingVO);
	
	/**
	 * 회원 정보 수정
	 * @param memvo
	 * @return 존재하지 않는 회원이라면 DataNotFoundException 발생 , INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult modifyMember(MemberVO memvo);
	
	/**
	 * 회원탈퇴 
	 * @param memvo
	 * @return 존재하지 않는 회원이라면 DataNotFoundException 발생 , INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult removeMember(MemberVO memvo);
	
}
