package kr.or.ddit.servlet03.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 
 * 학생관리를 위한 Business Logic Layer
 *
 */

// 2020-05-20
public interface IDDITStudentService {
	public ServiceResult createStudent(DDITStudentVO vo);
	public DDITStudentVO readStudent(String code);
	public ServiceResult modifyStudent(DDITStudentVO vo);
	public ServiceResult removeStudent(String code);
	
	
	public List<DDITStudentVO> readStudentList(PagingVO pagingVO);
	public int readStudentCount(PagingVO pagingVO);
}
