package kr.or.ddit.student.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

public class DDITStudentDAOImpl implements IDDITStudentDAO {
	private static IDDITStudentDAO self;
	public static IDDITStudentDAO getInstance() {
		if(self==null) self = new DDITStudentDAOImpl();
		return self;
	}
	
	private SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();

	@Override
	public String insertStudent(DDITStudentVO vo) {
		try {
			String code =(String) sqlMapClient.insert("Student.insertStudent", vo);
			return code;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int selectStudentCount(PagingVO<DDITStudentVO> pagingVO) {
		try {
			return (Integer) sqlMapClient.queryForObject("Student.selectStudentCount", pagingVO);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<DDITStudentVO> selectStudentList(PagingVO<DDITStudentVO> pagingVO) {
		try {
			return sqlMapClient.queryForList("Student.selectStudentList", pagingVO);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public DDITStudentVO selectStudent(String code) {
		try {
			return (DDITStudentVO) sqlMapClient.queryForObject("Student.selectStudent", code);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateStudent(DDITStudentVO vo) {
		try{
			return sqlMapClient.update("Student.updateStudent", vo);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteStudent(String code) {
		try{
			return sqlMapClient.delete("Student.deleteStudent", code);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
