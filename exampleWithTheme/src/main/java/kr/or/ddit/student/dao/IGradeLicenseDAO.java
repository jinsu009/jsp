package kr.or.ddit.student.dao;

import java.util.List;
import java.util.Map;

public interface IGradeLicenseDAO {
	public List<Map<String, Object>> selectGradeList();
	public List<Map<String, Object>> selectLicenseList();
}
