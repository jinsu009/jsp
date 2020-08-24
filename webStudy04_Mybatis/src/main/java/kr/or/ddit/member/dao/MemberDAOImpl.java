package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDAOImpl implements IMemberDAO{
	
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertMember(MemberVO memvo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);	
		){
			// mapper proxy 생성
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.insertMember(memvo);
		}
	}

	@Override
	public MemberVO selectMember(String mem_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			// mapper proxy 생성
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.selectMember(mem_id);
		}
	}

	@Override
	public int selectMemberCount(PagingVO pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			// mapper proxy 생성
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.selectMemberCount(pagingVO);
		}
	}

	@Override
	public List<MemberVO> selectMemberList(PagingVO pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			// mapper proxy 생성
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.selectMemberList(pagingVO);
		}
	}

	@Override
	public int updateMember(MemberVO memvo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);	
		){
			// mapper proxy 생성
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.updateMember(memvo);
		}
	}

	@Override
	public int deleteMember(String mem_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true); // true를 넣어줘서 commit를 해준다.
		){
			// mapper proxy 생성
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.deleteMember(mem_id);
		}
	}

}
