package kr.or.ddit.prod.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements IProdDAO {

	SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();
	
	@Override
	public String insertProd(ProdVO prod) {
		try{
			return (String) sqlMapClient.insert("Prod.insertProd", prod);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ProdVO selectProd(String prod_id) {
		try{
			return (ProdVO) sqlMapClient.queryForObject("Prod.selectProd", prod_id);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int selectProdCount(PagingVO<ProdVO> pagingVO) {
		try{
			return (Integer) sqlMapClient.queryForObject("Prod.selectProdCount", pagingVO);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO) {
		try{
			return sqlMapClient.queryForList("Prod.selectProdList", pagingVO);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try{
			return sqlMapClient.update("Prod.updateProd", prod);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
