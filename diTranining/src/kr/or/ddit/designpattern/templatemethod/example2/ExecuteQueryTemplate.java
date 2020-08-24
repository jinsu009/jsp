package kr.or.ddit.designpattern.templatemethod.example2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;

public abstract class ExecuteQueryTemplate<T> {

	public final List<T> queryForList(String query, Object[] parameters){
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
		){
		
			List<T> list = executeQuery(pstmt, parameters);
			return list;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public final T queryForObject(String query, Object[] parameters){
		List<T> list = queryForList(query, parameters);
		if(list!=null && list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
	protected abstract List<T> executeQuery(PreparedStatement pstmt, Object[] parameters) throws SQLException;
}
