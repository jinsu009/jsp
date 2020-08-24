package kr.or.ddit.servlet03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;

// 2020-05-20
public class GradeLicenseDAOImpl implements IGradeLicenseDAO {

//	private GradeLicenseDAOImpl() {} // new를 사용하지 못하게 하기 위해서	
	private static IGradeLicenseDAO self;
	public static IGradeLicenseDAO getInstance() {
		if(self==null) self = new GradeLicenseDAOImpl();
		return self;
	}
	
	@Override
	public List<Map<String, Object>> selectGradeList() {
		List<Map<String, Object>> gradeList = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CODE, TEXT FROM GRADE ");
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> record = new HashMap<>();
				gradeList.add(record);
				record.put("code",rs.getString("code"));
				record.put("text",rs.getString("text"));
			}
			return gradeList;
		}catch(SQLException e) { throw new RuntimeException(); }
	}

	@Override
	public List<Map<String, Object>> selectLicenseList() {
		List<Map<String, Object>> licenseList = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CODE, TEXT FROM LICENSE ");
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> record = new HashMap<>();
				licenseList.add(record);
				record.put("code", rs.getString("CODE"));
				record.put("text", rs.getString("TEXT"));
			}
			return licenseList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
