package kr.or.ddit.student.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 학생관리를 위한 Business Logic Layer
 *
 */
public interface IDDITStudentService {
	public ServiceResult createStudent(DDITStudentVO vo);
	public int readStudentCount(PagingVO<DDITStudentVO> pagingVO);
	public List<DDITStudentVO> readStudentList(PagingVO<DDITStudentVO> pagingVO);
	public DDITStudentVO readStudent(String code);
	public ServiceResult modifyStudent(DDITStudentVO vo);
	public ServiceResult removeStudent(String code);
}
