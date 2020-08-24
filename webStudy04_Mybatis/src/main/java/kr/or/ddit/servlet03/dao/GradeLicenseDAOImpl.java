package kr.or.ddit.servlet03.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;

public class GradeLicenseDAOImpl implements IGradeLicenseDAO {

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<Map<String, Object>> selectGradeList() {
		try(SqlSession sqlSession = sqlSessionFactory.openSession(true);){
			IGradeLicenseDAO mapper = sqlSession.getMapper(IGradeLicenseDAO.class);
			return mapper.selectGradeList();
		}
		
	}

	@Override
	public List<Map<String, Object>> selectLicenseList() {
		try(SqlSession sqlSession = sqlSessionFactory.openSession(true);){
			IGradeLicenseDAO mapper = sqlSession.getMapper(IGradeLicenseDAO.class);
			return mapper.selectLicenseList();
		}
	}

}
