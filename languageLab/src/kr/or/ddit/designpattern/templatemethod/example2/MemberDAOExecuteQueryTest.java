package kr.or.ddit.designpattern.templatemethod.example2;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;



public class MemberDAOExecuteQueryTest {

	ExecuteQueryTemplate<?> template = new MemberDAOExecuteQuery();
	
	@Test
	public void testQueryForList() {
		String query = "SELECT * FROM MEMBER WHERE MEM_ADD1 LIKE '%'||?||'%'";
		List<?> list = template.queryForList(query, new String[] {"대전"});
		System.out.println(list);
	}

	@Test
	public void testQueryForObject() {
		String query = "SELECT * FROM MEMBER WHERE mem_id = ?";
		MemberVO obj = (MemberVO)template.queryForObject(query, new String[] {"a001"});
		System.out.println(obj);
	}

}
