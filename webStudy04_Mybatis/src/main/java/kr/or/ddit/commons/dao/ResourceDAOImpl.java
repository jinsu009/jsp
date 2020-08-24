package kr.or.ddit.commons.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.ResourceVO;

public class ResourceDAOImpl implements IResourceDAO{

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<ResourceVO> selectResourceList() {
		try(SqlSession sqlSession = sqlSessionFactory.openSession(true);){
			IResourceDAO mapper = sqlSession.getMapper(IResourceDAO.class);
			return mapper.selectResourceList();
		}
	}

}
