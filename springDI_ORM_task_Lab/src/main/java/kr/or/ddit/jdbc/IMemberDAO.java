package kr.or.ddit.jdbc;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;

// 20-06-15
@Repository
public interface IMemberDAO {

	// 인터페이스라서 bean으로 등록될수 있는 구조가 아니다. 
	
	
	public MemberVO selectMember(String mem_id);
}
