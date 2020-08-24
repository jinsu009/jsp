package kr.or.ddit.prod.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.BuyerVO;

public class OtherDAOImpl implements IOthersDAO {

	SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();
	
	@Override
	public List<Map<String, Object>> selectLprodList() {
		try{ 
			return sqlMapClient.queryForList("Others.selectLprodList");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(String lprod_gu) {
		try{ 
			return sqlMapClient.queryForList("Others.selectBuyerList",lprod_gu);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
}
