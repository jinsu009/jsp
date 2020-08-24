package kr.or.ddit.db;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class CustomSqlSessionFactoryBuilderTest {

	@Test
	public void testGetSqlSessionFactory() {
		SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
		assertNotNull(sqlSessionFactory); // null이 아닐경우를 테스트
		PagingVO<MemberVO> pagingVO = new PagingVO<MemberVO>();
		pagingVO.setCurrentPage(1);
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
//			int count = (Integer) sqlSession.selectOne("kr.or.ddit.member.dao.IMemberDAO.selectMemberCount", pagingVO);
			// 1.(mapper 의) 프록시 생성 
			IMemberDAO memberDAOProxy = sqlSession.getMapper(IMemberDAO.class);
			int count = memberDAOProxy.selectMemberCount(pagingVO);
			assertNotEquals(0, count); // 0이 아닐경우를 테스트 
		}
	}

}
