package kr.or.ddit.servlet03.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;

public class GradeLicenseDAOImpl implements IGradeLicenseDAO {
	private GradeLicenseDAOImpl() {}

	private SqlMapClient client = CustomSqlMapClientBuilder.getSqlMapClient();

	private static GradeLicenseDAOImpl dao;

	public static GradeLicenseDAOImpl getInstance() {
		if (dao == null)
			dao = new GradeLicenseDAOImpl();
		return dao;
	}

	@Override
	public List<Map<String, Object>> selectGradeList() {
		List<Map<String, Object>> gradeList = null;
		try {
			gradeList = client.queryForList("GradeLicense.selectgrade");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return gradeList;
	}

	@Override
	public List<Map<String, Object>> selectLicenseList() {
		List<Map<String, Object>> licList = null;
		try {
			licList = client.queryForList("GradeLicense.selectlicense");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return licList;
	}
}
