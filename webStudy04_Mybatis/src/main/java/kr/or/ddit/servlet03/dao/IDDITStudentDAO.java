package kr.or.ddit.servlet03.dao;

import java.util.List;

import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;
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
	public List<DDITStudentVO> selectAllStudent(PagingVO pagingVO);
		
	/**
	 * 검색에 맞는 학생 정보 상세 조회
	 * @param code
	 * @return
	 */
	public DDITStudentVO selectStudent(String code);
	
	/**
	 * 학생정보 수정
	 * @param vo
	 * @return
	 */
	public int updateStudent(DDITStudentVO vo);
	
	/**
	 * 학생정보 삭제
	 * @param code
	 * @return
	 */
	public int deleteStudent(String code);

	/**
	 * 조건에 맞는 학생 수 조회
	 * @param vo
	 * @return
	 */
	public int selectStudentCount(PagingVO pagingVO);
}