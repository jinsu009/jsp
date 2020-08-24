package kr.or.ddit.member.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDAOImplTest {
	
	IMemberDAO memberDAO = new MemberDAOImpl();
	PagingVO<MemberVO> pagingVO = new PagingVO<MemberVO>();
	MemberVO memvo;
	
//	@Test
	public void testInsertMember() {
		MemberVO vo = new MemberVO();
		vo.setMem_id("q00");
		vo.setMem_pass("0000");
		vo.setMem_name("0605");
		vo.setMem_regno1("123456");
		vo.setMem_regno2("123456");
		vo.setMem_zip("321-547");
		vo.setMem_add1("216547");
		vo.setMem_add2("36547");
		vo.setMem_hometel("316547");
		vo.setMem_comtel("326547");
		vo.setMem_mail("aa.naver.com");
		vo.setMem_hp("010-3659-4545");
		int cnt = memberDAO.insertMember(vo);
		assertNotNull(vo);
	}

//	@Test
	public void testSelectMember() {
		MemberVO member = memberDAO.selectMember("b001");
		System.out.println(member.getMem_roles());
		assertNotNull(member);
	}

//	@Test
	public void testSelectMemberCount() {
		int cnt = memberDAO.selectMemberCount(pagingVO);
		assertEquals(0, cnt);
		System.out.println("\n selectMemberCountt \n");
	}

//	@Test
	public void testSelectMemberList() {
		List<MemberVO> memList = memberDAO.selectMemberList(pagingVO);
		assertNotNull(memList);
		System.out.println("\n testSelectMemberList \n");
	}

//	@Test
	public void testUpdateMember() {
		MemberVO vo = new MemberVO("z000","0000");
		vo.setMem_name("라이언");
		int cnt = memberDAO.updateMember(memvo);
		assertNotEquals(0, cnt);
	}

//	@Test
	public void testDeleteMember() {
		int cnt = memberDAO.deleteMember("z999");
		System.out.println("\n delete \n");
		assertNotEquals(0, cnt); // 삭제가 정상적으로 되면 0이 아니기 때문에 not equal 사용 
	}

}
