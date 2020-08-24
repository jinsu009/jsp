package kr.or.ddit.commons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

import java.sql.Statement;

// 2020-05-19
public class AuthenticateDAOImpl implements IAuthenticateDAO {

	// 싱글톤 패턴 > 기본생성자를 private로 묶는다
	private AuthenticateDAOImpl() {}
	private static IAuthenticateDAO self;
	public static IAuthenticateDAO getInstance() {
		if(self==null) self = new AuthenticateDAOImpl();
		return self;
	}
	
	@Override
	public MemberVO selectMember(MemberVO member) {
		MemberVO savedMember = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select MEM_ID, MEM_PASS, MEM_NAME, MEM_ADD1, MEM_ADD2, MEM_HP, MEM_MAIL ");
		sql.append(" from MEMBER ");
		sql.append(" where MEM_ID= ? AND MEM_PASS= ? ");
		
		System.out.println(sql);
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
		){
			stmt.setString(1, member.getMem_id());
			stmt.setString(2, member.getMem_pass());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				savedMember = new MemberVO();
				savedMember.setMem_id(rs.getString("MEM_ID"));
				savedMember.setMem_pass(rs.getString("MEM_PASS"));
				savedMember.setMem_name(rs.getString("MEM_NAME"));
				savedMember.setMem_add1(rs.getString("MEM_ADD1"));
				savedMember.setMem_add2(rs.getString("MEM_ADD2"));
				savedMember.setMem_hp(rs.getString("MEM_HP"));
				savedMember.setMem_mail(rs.getString("MEM_MAIL"));
			}
			return savedMember;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
