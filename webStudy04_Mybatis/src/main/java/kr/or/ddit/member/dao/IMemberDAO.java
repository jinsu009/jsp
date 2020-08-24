package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원관리(CRUD)를 위한 Persistence
 * 
 *
 */

// 2020-05-25
public interface IMemberDAO {
	/**
	 * 신규등록 
	 * @param memvo
	 * @return 등록된 회원수
	 */
	public int insertMember(MemberVO memvo);
	
	/**
	 * 회원정보 상세 조회
	 * @param mem_id
	 * @return 존재하지 않으면 null 반환
	 */
	public MemberVO selectMember(String mem_id);
	
	/**
	 * 검색 조건에 맞는 회원수 조회
	 * @return
	 */
	public int selectMemberCount(PagingVO pagingVO);
	
	/**
	 * 검색조건에 맞는 회원 목록 조회
	 * @return
	 */
	public List<MemberVO> selectMemberList(PagingVO pagingVO);
	
	/**
	 * 회원정보 수정
	 * @param memvo
	 * @return
	 */
	public int updateMember(MemberVO memvo);
	
	/**
	 * 회원정보 삭제 
	 * @param mem_id
	 * @return
	 */
	public int deleteMember(String mem_id);
}
