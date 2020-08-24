package kr.or.ddit.student.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;

public class GradeLicenseDAOImpl implements IGradeLicenseDAO {
	private GradeLicenseDAOImpl() {}
	private static GradeLicenseDAOImpl self;
	public static GradeLicenseDAOImpl getInstance() {
		if(self==null) self = new GradeLicenseDAOImpl();
		return self;
	}
	
	private SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();
	@Override
	public List<Map<String, Object>> selectGradeList() {
		try {
			return sqlMapClient.queryForList("GradeLicense.selectGradeList");
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Map<String, Object>> selectLicenseList() {
		try {
			return sqlMapClient.queryForList("GradeLicense.selectLicenseList");
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
