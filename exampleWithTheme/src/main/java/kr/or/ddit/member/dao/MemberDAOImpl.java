package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDAOImpl implements IMemberDAO {
	private static MemberDAOImpl self;
	private MemberDAOImpl() {}
	public static MemberDAOImpl getInstance() {
		if(self==null) self = new MemberDAOImpl();
		return self;
	}
	private  SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();
	
	@Override
	public int insertMember(MemberVO member) {
		try{
			return sqlMapClient.update("Member.insertMember", member);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public MemberVO selectMember(String mem_id) {
		try{
			return (MemberVO) sqlMapClient.queryForObject("Member.selectMember", mem_id);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int selectMemberCount(PagingVO pagingVO) {
		try{
			return (Integer)sqlMapClient.queryForObject("Member.selectMemberCount", pagingVO);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MemberVO> selectMemberList(PagingVO pagingVO) {
		try{
			return sqlMapClient.queryForList("Member.selectMemberList", pagingVO);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try{
			return sqlMapClient.update("Member.updateMember", member);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteMember(String mem_id) {
		try{
			return sqlMapClient.delete("Member.deleteMember", mem_id);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
