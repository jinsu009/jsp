package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

// 2020-05-25
public class MemberDAOImpl implements IMemberDAO {

	private SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();

	private MemberDAOImpl() {}
	private static MemberDAOImpl self;
	public static IMemberDAO getInstance() {
		if (self == null)
			self = new MemberDAOImpl();
		return self;
	}

	@Override
	public int insertMember(MemberVO memvo) {
		try{ 
			return sqlMapClient.update("Member.insertMember", memvo);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public MemberVO selectMember(String mem_id) {
		try{ 
			return (MemberVO)sqlMapClient.queryForObject("Member.selectMember", mem_id);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int selectMemberCount(PagingVO pagingVO) {
		try {
			return (Integer)sqlMapClient.queryForObject("Member.selectMemberCount",pagingVO);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MemberVO> selectMemberList(PagingVO pagingVO) {
		try {
			return sqlMapClient.queryForList("Member.selectMemberList",pagingVO);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMember(MemberVO memvo) {
		try{ 
			return sqlMapClient.update("Member.updateMember", memvo);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteMember(String mem_id) {
		try{ 
			return sqlMapClient.delete("Member.deleteMember",mem_id);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
