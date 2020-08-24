package kr.or.ddit.commons.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.ResourceVO;

public class ResourceDAOImpl implements IResourceDAO{

	
	private SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();

//	private ResourceDAOImpl() {}
//	private static ResourceDAOImpl self;
//	public static IResourceDAO getInstance() {
//		if(self == null)
//			self = new ResourceDAOImpl();
//		return self;
//	}
	
	@Override
	public List<ResourceVO> selectResourceList() {
		try{ 
			return sqlMapClient.queryForList("Resource.selectResourceList");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
