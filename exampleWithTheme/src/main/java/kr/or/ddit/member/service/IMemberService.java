package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원 관리(CRUD)를 위한 Business Logic Layer
 *
 */
/**
 * @param member
 * @return
 */
/**
 * @author SEM-PC
 *
 */
public interface IMemberService {
	/**
	 * 신규회원 등록
	 * @param member
	 * @return PKDUPLICATED, OK, FAIL
	 */
	public ServiceResult createMember(MemberVO member);
	/**
	 * 회원 정보 상세 조회
	 * @param mem_id
	 * @return 존재하지 않는다면, DataNotFoundException 발생
	 */
	public MemberVO readMember(String mem_id);
	/**
	 * 검색 조건에 맞는 등록 회원수
	 * @param pagingVO TODO
	 * @return
	 */
	public int readMemberCount(PagingVO pagingVO);
	/**
	 * 검색 조건에 맞는 회원 목록
	 * @param pagingVO TODO
	 * @return
	 */
	public List<MemberVO> readMemberList(PagingVO pagingVO);
	/**
	 * 회원정보 수정
	 * @param member
	 * @return 존재하지 않는다면, DataNotFoundException, INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult modifyMember(MemberVO member);
	/**
	 * 회원 탈퇴
	 * @param member
	 * @return 존재하지 않는다면, DataNotFoundException, INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult removeMember(MemberVO member);
}







