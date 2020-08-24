package kr.or.ddit.member.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.WebAppTestContext;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@RunWith(SpringRunner.class) // 동작환경구출
@WebAppTestContext
public class MemberDAOImplTest {
/**
 * pom에 추가한 spring test를 사용해서 새로운 annotation 적용해보자	
 */
	@Inject
	IMemberDAO memberDAO;
	
	MemberVO member;
	PagingVO<MemberVO> pagingVO;
	
	@Before
	public void setup() {
		pagingVO = new PagingVO<MemberVO>();
		pagingVO.setCurrentPage(1);
		member = new MemberVO();
		member.setMem_id("a009");
		member.setMem_name("신규");
		member.setMem_pass("1004");
		member.setMem_regno1("111111");
		member.setMem_regno2("1111111");
		member.setMem_zip("000-000");
		member.setMem_add1("대전 대흥동");
		member.setMem_add2("영민빌딩");
		member.setMem_mail("aa.naver.com");
		member.setMem_hp("000-000-0000");
		member.setMem_hometel("000-000-000");
		member.setMem_comtel("000-000-000");
	}


	@Test
	public void testSelectMember() {
 		MemberVO member = memberDAO.selectMember("c001");
 		System.out.println(member.getMem_roles());
 		assertNotNull(member);
	}

	@Test
	public void testSelectMemberCount() {
		int count = memberDAO.selectMemberCount(pagingVO);
		assertNotEquals(0, count);
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> list = memberDAO.selectMemberList(pagingVO);
		assertNotNull(list);
		assertNotEquals(0, list.size());
	}

	@Test
	public void testInsertMember() {
		int rowcnt = memberDAO.insertMember(member);
		assertNotEquals(0, rowcnt);
	}
	
	@Test
	public void testUpdateMember() {
		int rowcnt = memberDAO.updateMember(member);
		assertEquals(0, rowcnt);
	}

	@Test
	public void testDeleteMember() {
		int rowcnt = memberDAO.deleteMember("d001");
		assertEquals(1, rowcnt);
	}
}
