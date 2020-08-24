package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.vo.BuyerVO;

public class OthersDAOImpl implements IOthersDAO {

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<Map<String, Object>> selectLprodList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			// mapper proxy 생성
			IOthersDAO mapper = sqlSession.getMapper(IOthersDAO.class);
			return mapper.selectLprodList();
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(String lprod_gu) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			// mapper proxy 생성
			IOthersDAO mapper = sqlSession.getMapper(IOthersDAO.class);
			return mapper.selectBuyerList(lprod_gu);
		}
	}

}
