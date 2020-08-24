package kr.or.ddit.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import kr.or.ddit.vo.MemberVO;

public class SpringJDBCTestView {

	public static void main(String args[]) {
		// container 생성
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"kr/or/ddit/jdbc/conf/datasource-context.xml");
		context.registerShutdownHook(); // 종료될때 메모리 정리
		DataSource ds = context.getBean(DataSource.class);

		JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
		
		// namedParameter
		NamedParameterJdbcTemplate namedTemplate =
				context.getBean(NamedParameterJdbcTemplate.class);
		
		String sql = "SELECT * FROM MEMBER ";
		List<MemberVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
		System.out.println(list);
		
		sql = "SELECT * FROM MEMBER WHERE MEM_ID = ? "
				+ "AND MEM_NAME LIKE ('%' || ? || '%')";
		list = jdbcTemplate.query(sql, new Object[] {"a001","은대"} ,
				new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
		
		System.out.println(list);
		
		sql = "SELECT * FROM MEMBER WHERE MEM_ID = :mem_id "
				+ "AND MEM_NAME LIKE ('%' || :mem_name || '%')";
		// map : 데이터의 순서가 상관이 없고 키 로 인해 식별성을 가지게 된다. 
		Map<String, Object> map = new HashMap<>();
		map.put("mem_id","a001");
		map.put("mem_name","은대");
		list = namedTemplate.query(sql, map, new BeanPropertyRowMapper(MemberVO.class));
		System.out.println(list);
	}
}
