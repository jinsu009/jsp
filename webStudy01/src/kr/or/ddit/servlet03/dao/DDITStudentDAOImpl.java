package kr.or.ddit.servlet03.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DDITStudentVO;

// 2020-05-20
public class DDITStudentDAOImpl implements IDDITStudentDAO {

	private DDITStudentDAOImpl() {}
	private static IDDITStudentDAO self;
	public static IDDITStudentDAO getInstance() {
		if(self==null) self = new DDITStudentDAOImpl();
		return self;
	}
	
	private String generateCode(DDITStudentVO vo) {
		String genCode = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select 'S' || lpad(to_number(substr(MAX(CODE),2))+1,3,'0') ");
		sql.append(" from dditstudent ");
		try (
				Connection conn = ConnectionFactory.getConnection();                  
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) 
		{
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				genCode = rs.getString(1);
			}
			vo.setCode(genCode);
			return genCode;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private int insertStdLicense(DDITStudentVO vo) {
	
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into std_license(std_code, lic_code) values(?,?)");
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			int cnt = 0; 
			for(String tmp : vo.getLic_codes()) {
				pstmt.setString(1, vo.getCode());
				pstmt.setString(2, tmp);
				cnt += pstmt.executeUpdate();
			}
			return cnt;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private int deleteLicenses(String code) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM STD_LICENSE WHERE STD_CODE = ? ");
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, code);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int insertStudent(DDITStudentVO vo) {
		// 트랜잭션 관리 : 원자성 (더이상 쪼갤수 없는 작업의 단위) 
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT ALL");
		sql.append(" INTO DDITSTUDENT (                       ");
		sql.append("     CODE,		    NAME,		    BIRTHDAY,    ");
		sql.append("     AGE,		    GR_CODE,		    GEN,     ");
		sql.append("     CAREER                                      ");
		sql.append(" ) VALUES (                                      ");
		sql.append(" 		?,		    ?,		    TO_DATE(?, 'YYYY-MM-DD'),");
		sql.append(" 	    ?,		    ?,		    ?,               ");
		sql.append(" 	    ?                                        ");
		sql.append(" )                                               ");
		if(vo.getLic_codes()!=null) {
			for(String tmp :vo.getLic_codes()) {
				sql.append(" INTO STD_LICENSE (STD_CODE, LIC_CODE) VALUES(?,?) ");
			}
		}
		sql.append(" SELECT * FROM DUAL");
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			generateCode(vo);
			int i = 1;
			pstmt.setString(i++, vo.getCode());
			pstmt.setString(i++, vo.getName());
			pstmt.setString(i++, vo.getBirthday());
			pstmt.setInt(i++, vo.getAge());
			pstmt.setString(i++, vo.getGrade());
			pstmt.setString(i++, vo.getGender());
			pstmt.setString(i++, vo.getCareer());
			
			if(vo.getLic_codes()!=null) {
				for(String lic_code : vo.getLic_codes()) {
					pstmt.setString(i++, vo.getCode());
					pstmt.setString(i++, lic_code);
				}
			}
			
			int cnt = pstmt.executeUpdate();
			cnt += insertStdLicense(vo);
			
			return cnt;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DDITStudentVO> selectAllStudent() {
		
		List<DDITStudentVO> stuList = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT code, name, to_char(birthday,'yyyy-MM-dd') birthday , age, gr_code, DECODE(gen, 'M', '남', '여') GEN, career");
		sql.append(" FROM dditstudent ");
			
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
		){

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				DDITStudentVO svo = new DDITStudentVO();
				svo.setCode(rs.getString("code"));
				svo.setName(rs.getString("name"));
				svo.setBirthday(rs.getString("birthday"));
				svo.setAge(rs.getInt("age"));
				svo.setGrade(rs.getString("gr_code"));
				svo.setGender(rs.getString("gen"));
				svo.setCareer(rs.getString("career"));
				stuList.add(svo);
			}
			
			return stuList;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public DDITStudentVO selectStudent(String code) {
		DDITStudentVO dsvo = new DDITStudentVO();
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.code, name, to_char(birthday, 'yyyy-mm-dd') birthday ");
		sql.append(" ,age, b.text grade, decode(gen, 'M', '남','여') gen, career, d.text license ");
		sql.append(" from dditstudent a inner join grade b on (gr_code = b.code) ");
		sql.append("  left outer join std_license c on (a.code = c.std_code) ");
		sql.append(" left outer join license d on (c.lic_code = d.code) ");
		sql.append(" where a.code= ? ");
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			
			Map<String, DDITStudentVO> resultMap = new HashMap<>();
			
			while(rs.next()) {
				String tmp = rs.getString("code");
				dsvo = resultMap.get(tmp);
				if(dsvo==null) {
					dsvo = new DDITStudentVO();
					resultMap.put(tmp,dsvo);
				}
				dsvo.setCode(tmp);
				dsvo.setName(rs.getString("name"));
				dsvo.setBirthday(rs.getString("birthday"));
				dsvo.setAge(rs.getInt("age"));
				dsvo.setGrade(rs.getString("grade"));
				dsvo.setGender(rs.getString("gen"));
				dsvo.setCareer(rs.getString("career"));
				
				List<String> license = dsvo.getLicense();
				if(license==null) {
					license = new ArrayList<>();
					dsvo.setLicense(license);
				}
				license.add(rs.getString("license"));
				
			}
			if(resultMap.values().size()>0) {return resultMap.get(code);}
			else {return null;}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

	@Override
	public int updateStudent(DDITStudentVO vo) {
		int cnt = 0;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE dditstudent ");
		sql.append(" SET NAME = ? , birthday = ?, age = ?, gr_code = ? ,  gen = ? , career =?");
		sql.append(" WHERE CODE = ?");
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());		
		){
			int i = 1;
			pstmt.setString(i++, vo.getName());
			pstmt.setString(i++, vo.getBirthday());
			pstmt.setInt(i++, vo.getAge());
			pstmt.setString(i++, vo.getGrade());
			pstmt.setString(i++, vo.getGender());
			pstmt.setString(i++, vo.getCareer());
			pstmt.setString(i++, vo.getCode());
			cnt += deleteLicenses(vo.getCode());
			
			if(vo.getLic_codes()!=null) cnt += insertStdLicense(vo);
			cnt = pstmt.executeUpdate();
			return cnt;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteStudent(String code) {
		int cnt = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM DDITSTUDENT WHERE CODE = ?");
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, code);
			cnt = deleteLicenses(code);
			cnt += pstmt.executeUpdate();
			return cnt;
		}catch(SQLException e) { throw new RuntimeException(e);}
	}
}
