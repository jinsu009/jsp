package kr.or.ddit.servlet03.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

public class DDITStudentDAOImpl implements IDDITStudentDAO{
	private DDITStudentDAOImpl() {}
	private SqlMapClient client = CustomSqlMapClientBuilder.getSqlMapClient();
	
	private static DDITStudentDAOImpl dao;
	
	public static DDITStudentDAOImpl getInstance() {
		if(dao==null) dao = new DDITStudentDAOImpl();
		return dao;
	}
	
	@Override
	public String insertStudent(DDITStudentVO vo) {
		try {
			String code = (String)client.insert("Student.insertStudent",vo);
			return code;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DDITStudentVO> selectAllStudent(PagingVO pagingVO) {
		try {
			return client.queryForList("Student.selectAllStudent",pagingVO);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public DDITStudentVO selectStudent(String code) {
		DDITStudentVO dvo = null;
		try {
			return (DDITStudentVO)client.queryForObject("Student.selectStudent",code);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateStudent(DDITStudentVO vo) {
		int cnt = 0;
		try {
			return client.update("Student.updateStudent",vo);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteStudent(String code) {
		int cnt = 0;
		try {
			return client.delete("Student.deleteStudent",code);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int selectStudentCount(PagingVO pagingVO) {
		try {
			return (Integer)client.queryForObject("Student.selectStudentCount",pagingVO);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
