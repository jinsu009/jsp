package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImpl implements IBuyerDAO {
	SqlMapClient sqlMapClient =
			CustomSqlMapClientBuilder.getSqlMapClient();

	@Override
	public int selectBuyerCount(PagingVO<BuyerVO> pagingVO) {
		try {
			return (Integer)sqlMapClient.queryForObject("Buyer.selectBuyerCount", pagingVO);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO) {
		try {
			return sqlMapClient.queryForList("Buyer.selectBuyerList", pagingVO);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public BuyerVO selectBuyer(String buyer_id) {
		try {
			return (BuyerVO) sqlMapClient.queryForObject("Buyer.selectBuyer", buyer_id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String insertBuyer(BuyerVO buyer) {
		try {
			return (String) sqlMapClient.insert("Buyer.insertBuyer", buyer);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		try {
			return sqlMapClient.update("Buyer.updateBuyer", buyer);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
