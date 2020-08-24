package homework.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import homework.db.CustomSqlMapClientBuilder;
import homework.enums.ServiceResult;
import homework.vo.BuyerVO;
import homework.vo.PagingVO;


public class BuyerDAOImpl implements IBuyerDAO{

	private SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();
	
	private BuyerDAOImpl() {}
	private static BuyerDAOImpl self;
	public static IBuyerDAO getInstance() {
		if(self==null) self = new BuyerDAOImpl();
		return self;
	}
	
	@Override
	public String createBuyer(BuyerVO buyvo) {
		try{ 
			String code = (String)sqlMapClient.insert("buyer.createBuyer",buyvo);
			return code;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public BuyerVO readBuyer(String buyer_id) {
		try {
		return (BuyerVO)sqlMapClient.queryForObject("buyer.readBuyer", buyer_id);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int readBuyerCount(PagingVO pagingVO) {
		try{ 
			return (Integer)sqlMapClient.queryForObject("buyer.readBuyerCount",pagingVO);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<BuyerVO> readBuyerList(PagingVO pagingVO) {
		try{ 
			return sqlMapClient.queryForList("buyer.readBuyerList",pagingVO);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int modifyBuyer(BuyerVO buyvo) {
		try{ 
			return sqlMapClient.update("buyer.modifyBuyer",buyvo);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ServiceResult removeBuyer(BuyerVO buyvo) {
		// TODO Auto-generated method stub
		return null;
	}

}
