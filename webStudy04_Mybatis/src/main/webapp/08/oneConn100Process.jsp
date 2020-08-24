<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>08/oneConnOneProcess/0521</title>
	</head>
	<body>
	<%
		long startTime = System.currentTimeMillis();
		String sql = "select mem_name from member where mem_id ='a001'";
		try (Connection conn = ConnectionFactory.getConnection();) {
			for (int i = 1; i <= 100; i++) {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				String name = null;
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					name = rs.getString(1);
				}
				out.println(name);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		long endTime = System.currentTimeMillis();
	%>
	<p style="color: red">
		소요시간 :
		<%=(endTime - startTime)%></p>

</body>
</html>