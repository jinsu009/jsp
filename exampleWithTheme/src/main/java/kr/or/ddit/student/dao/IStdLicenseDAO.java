package kr.or.ddit.student.dao;

import kr.or.ddit.vo.DDITStudentVO;

public interface IStdLicenseDAO {
	public int insertLicenses(DDITStudentVO vo);
	public int deleteLicenses(String code);
}
