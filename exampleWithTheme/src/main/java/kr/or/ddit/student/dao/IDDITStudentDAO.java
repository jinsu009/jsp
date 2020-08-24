package kr.or.ddit.student.dao;

import java.util.List;

import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

public interface IDDITStudentDAO {

	public String insertStudent(DDITStudentVO vo);

	public int selectStudentCount(PagingVO<DDITStudentVO> pagingVO);
	
	public List<DDITStudentVO> selectStudentList(PagingVO<DDITStudentVO> pagingVO);
	
	public DDITStudentVO selectStudent(String code);
	
	public int updateStudent(DDITStudentVO vo);
	
	public int deleteStudent(String code);

}