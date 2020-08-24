package homework.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import homework.db.CustomSqlMapClientBuilder;

public class LprodDAOImpl implements ILprodDAO {

	private LprodDAOImpl() {}
	
	private SqlMapClient sqlmapclient = CustomSqlMapClientBuilder.getSqlMapClient();
	
	private static LprodDAOImpl dao;
	
	public static LprodDAOImpl getInstance() {
		if(dao==null)
			dao = new LprodDAOImpl();
		return dao;
	}
	
	@Override
	public List<Map<String, Object>> LprodList() {
		List<Map<String, Object>> LprodList = null;
		try{ 
			LprodList = sqlmapclient.queryForList("Lprod.LprodList");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return LprodList;
	}

}
