package kr.or.ddit.servlet03.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

public class DDITStudentDAOImplTest {
	
	DDITStudentVO dvo = new DDITStudentVO();
	PagingVO<DDITStudentVO> pagingVO = new PagingVO<DDITStudentVO>();
	IDDITStudentDAO dao = new DDITStudentDAOImpl();

//	@Test
	public void testInsertStudent() {
		DDITStudentVO vo = new DDITStudentVO();
		vo.setName("테스트");
		vo.setBirthday("1990-01-05");
		vo.setGender("F");
		vo.setAge(23);
		vo.setLic_codes(new String[] {"L002","L001"});
		int cnt = dao.insertStudent(vo);
		assertEquals(0, cnt);
	}

//	@Test
	public void testSelectAllStudent() {
//		List<DDITStudentVO> stdList = dao.selectAllStudent();
//		assertNotNull(stdList);
//		assertEquals(false, stdList.isEmpty());
	}

//	@Test
	public void testSelectStudent() {
		fail("Not yet implemented");
	}

//	@Test
	public void testUpdateStudent() {
		DDITStudentVO vo = new DDITStudentVO();
		vo.setName("테스트");
		vo.setBirthday("1990-01-05");
		vo.setGender("F");
		vo.setAge(23);
		vo.setLic_codes(new String[] {"L002","L001"});
//		String cnt = dao.insertStudent(vo);
		assertNotNull(vo);
	}

//	@Test
	public void testDeleteStudent() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSelectStudentCount() {
		int cnt = dao.selectStudentCount(pagingVO);
		assertEquals(0, cnt);
		
	}

}
