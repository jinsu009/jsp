package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements IProdDAO {

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertProd(ProdVO prod) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.insertProd(prod);
		}
	}

	@Override
	public ProdVO selectProd(String prod_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProd(prod_id);
		}
	}

	@Override
	public int selectProdCount(PagingVO<ProdVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProdCount(pagingVO);
		}
	}

	@Override
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProdList(pagingVO);
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.updateProd(prod);
		}
	}
}
