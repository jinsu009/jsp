package kr.or.ddit.db;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.Map;

import org.junit.Test;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CustomSqlMapClientBuilderTest {

	@Test
	public void testGetSqlMapClient() throws SQLException {
		SqlMapClient client = CustomSqlMapClientBuilder.getSqlMapClient();
		assertNotNull(client);
		Map<String, Object> map = client.queryForMap("selectMember", null, "mem_id");
		assertNotNull(map);
		System.out.println(map);
	}

}
