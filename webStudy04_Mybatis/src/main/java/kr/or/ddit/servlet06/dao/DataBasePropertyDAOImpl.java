package kr.or.ddit.servlet06.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;
//2020-05-19
public class DataBasePropertyDAOImpl implements IDataBasePropertyDAO {

	@Override
	public List<DataBasePropertyVO> selectDataBaseProperties(Map<String, Object> modelMap) {
		try(
				Connection conn = ConnectionFactory.getConnection();
				// DB로 명령 전달
				Statement stmt =  conn.createStatement();
			){
		
				String sql = "select PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION from DATABASE_PROPERTIES";
				ResultSet rs = stmt.executeQuery(sql);
				// set : 순서, index가 존재하지 않는다. > while문으로 처음부터 끝까지 출력
				// next로 pointer를 header에서 다음번째로 넘겨놓는다.
				
				ResultSetMetaData rsmd = rs.getMetaData();
				int cnt = rsmd.getColumnCount();
				String[] headers = new String[cnt];
				modelMap.put("headers",headers);
				for(int i=1; i<=cnt; i++){
					headers[i-1] = rsmd.getColumnName(i);
				}
				List<DataBasePropertyVO> propList = new ArrayList<>();
				modelMap.put("propList",propList);
				while(rs.next()){
					DataBasePropertyVO vo = new DataBasePropertyVO(); 
					propList.add(vo);
					vo.setProperty_name(rs.getString("PROPERTY_NAME"));
					vo.setProperty_value(rs.getString(2)); // Oracle은 1부터 시작
					vo.setDescription(rs.getString("DESCRIPTION"));
				}
				return propList;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
