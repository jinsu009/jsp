package kr.or.ddit.servlet03.dao;

import java.util.List;
import java.util.Map;

// 2020-05-20
public interface IGradeLicenseDAO {
	public List<Map<String, Object>> selectGradeList();
	public List<Map<String, Object>> selectLicenseList();
}
