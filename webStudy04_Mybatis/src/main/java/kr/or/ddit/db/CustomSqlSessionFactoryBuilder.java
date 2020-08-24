package kr.or.ddit.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {

	private static SqlSessionFactory SqlSessionFactory;
	static {
		try(
			Reader reader = Resources.getResourceAsReader("kr/or/ddit/db/mybaits/Configuration.xml");
		){
			SqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch (IOException e) { // xml 오류 발생
			throw new RuntimeException(e);
		}
	}
	public static SqlSessionFactory getSqlSessionFactory() {
		return SqlSessionFactory;
	}
	
}
