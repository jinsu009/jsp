package kr.or.ddit.servlet03.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.DDITStudentVO;

public class StdLicenseDAOImpl implements IStdLicenseDAO {

	private StdLicenseDAOImpl() {}
	private static StdLicenseDAOImpl dao;
	public static StdLicenseDAOImpl getInstance() {
		if(dao==null) dao = new StdLicenseDAOImpl();
		return dao;
	}
	
	private SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();
	
	@Override
	public int insertLicenses(DDITStudentVO vo) {
		 //ibatis 를 이용한 insert 쿼리 실행
	      //1) selectKey를 PK를 쿼리 전에 생서하는 경우 insert 메소드를 실행 -> 생성된 PK가 반환
	      //2) selectKey가 없는 단수 쿼리, update/delete 메소드 실행 -> 영향을 받은 레코드 수가 반환됨!
	      try{
	         return sqlMapClient.update("StdLicense.insertLisenses", vo);
	      }catch(SQLException e){
	         throw new RuntimeException(e);
	      }
	}
	
	   @Override
	   public int deleteLicenses(String code) {
	      try{
	         return sqlMapClient.delete("StdLicense.deleteLicenses", code);
	      }catch(SQLException e){
	         throw new RuntimeException(e);
	      }
	   }

}
