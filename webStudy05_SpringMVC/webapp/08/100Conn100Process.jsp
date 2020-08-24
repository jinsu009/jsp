<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>08/100Conn100Process.jsp/0521</title>
	</head>
	<body>
	<%
		long startTime = System.currentTimeMillis();
		String sql = "select mem_name from member where mem_id ='a001'";
		for (int i = 1; i <= 100; i++) {
			String name = null;
			try (Connection conn = ConnectionFactory.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);) {
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					name = rs.getString(1);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			out.println(name);
		}
		long endTime = System.currentTimeMillis();
	%>
	<p style="color: red">
		소요시간 :
		<%=(endTime - startTime)%></p>
</body>
</html>