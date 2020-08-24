package homework.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

//2020-05-19 , 2020-05-21
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	private static DataSource ds;
	

	static {
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo");
//		try {
//			Class.forName(bundle.getString("driverClassName"));
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);
//		}
		// 전혀 수정할 필요가 없는 코드와 수정 발생 코드가 섞여있다면, 분리시켜야 한다.
		// 분리하는 과정에서 다른 pattern을 적용시킬수 있다.
		// layerd architecture
		 url = bundle.getString("url").trim();
		 user = bundle.getString("user").trim();
		 password = bundle.getString("password").trim();
		 BasicDataSource ods = new BasicDataSource();
		 ods.setDriverClassName(bundle.getString("driverClassName"));
		 ods.setUrl(url);
		 ods.setUsername(user);
		 ods.setPassword(password);
		 ods.setInitialSize(5); // 처음 connection 생성 갯수 
		 ods.setMaxActive(10); // connection의 최대 생성 갯수
		 ods.setMaxWait(2000);
		 ds = ods;
	}

	public static Connection getConnection() throws SQLException {
		// ---connection
		// socket 통신을 위해 포트번호 1521 을 작성한다.
//		Connection conn = DriverManager.getConnection(url, user, password);
		return ds.getConnection();
	}
}
