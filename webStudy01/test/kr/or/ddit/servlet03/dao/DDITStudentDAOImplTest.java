package kr.or.ddit.servlet03.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.DDITStudentVO;

class DDITStudentDAOImplTest {

	IDDITStudentDAO dao = DDITStudentDAOImpl.getInstance();
	
	@Test
	void testInsertStudent() {
		fail("Not yet implemented");
	}
	@Test
	void testSelectAllStudent() {
		List<DDITStudentVO> stdList = dao.selectAllStudent();
		assertNotNull(stdList);
		assertEquals(false, stdList.isEmpty());
	}

	@Test
	void testSelectStudent() {
		DDITStudentVO vo = dao.selectStudent("S001");
		assertNotNull(vo);
		assertEquals(2, vo.getLicense().size());
		System.out.println(vo.getLicense());
	}

	@Test
	void testUpdateStudent() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteStudent() {
		fail("Not yet implemented");
	}

}
