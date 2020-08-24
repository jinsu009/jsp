package kr.or.ddit.servlet03.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.DDITStudentVO;

public class StdLicenseDAOImpl implements IStdLicenseDAO {

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertLicenses(DDITStudentVO vo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			IStdLicenseDAO mapper = sqlSession.getMapper(IStdLicenseDAO.class);
			return mapper.insertLicenses(vo);
		}
	}

	@Override
	public int deleteLicenses(String code) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			IStdLicenseDAO mapper = sqlSession.getMapper(IStdLicenseDAO.class);
			return mapper.deleteLicenses(code);
		}
	}

}
