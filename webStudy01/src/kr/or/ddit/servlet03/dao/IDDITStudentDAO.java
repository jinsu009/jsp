package kr.or.ddit.servlet03.dao;

import java.util.List;

import kr.or.ddit.vo.DDITStudentVO;
// 2020-05-20
public interface IDDITStudentDAO {

	/**
	 * 학생의 정보를 담아준다.
	 * @param vo
	 * @return 성공 : 1, 실패 : 0
	 */
	public int insertStudent(DDITStudentVO vo);

	/**
	 * 모든 학생의 정보를 list로 넘겨준다. 
	 * @return List<DDITStudentVO>
	 */
	public List<DDITStudentVO> selectAllStudent();
	
	public DDITStudentVO selectStudent(String code);
	
	public int updateStudent(DDITStudentVO vo);
	
	public int deleteStudent(String code);
}