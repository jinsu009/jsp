<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/jdbcDesc.jsp</title>
</head>
<body>
<h4>JDBC (Java DataBase Connectivity)</h4>
<pre>
	1. 벤더가 제공하는 드라이버를 찾아 빌드패스에 추가
	2. ClassLoader 를 통해 드라이버 클래스 로딩
	3. Connection 생성
	4. Query object 생성
		1) Statement : 동적 쿼리
		2) PreparedStatement : 쿼리 객체 생성 시 미리 컴파일된 쿼리 객체,쿼리 파라미터(?,  literal) 
		3) CallableStatement : 프로시저나 function 를 호출할때 사용하는 쿼리 객체
	5. Query 실행 : DML, DCL, TCL, DDL
		ResultSet executeQuery() : select
		int executeUpdate() : insert, update, delete
	6. ResultSet 핸들링
	7. Release 	
	</pre>
<table>
	<thead>
		<tr>
		<%
			Map<String, Object> modelMap = (Map) request.getAttribute("modelMap");
			String[] headers = (String[]) modelMap.get("headers");
			List<DataBasePropertyVO> propList = (List) modelMap.get("propList");
			for(String header : headers){
				%>
				<th><%=header %></th>
				<%
			}
		%>
		</tr>
	</thead>
	<tbody>
		<%
			for(DataBasePropertyVO tmp : propList){
				%>
				<tr>
					<td><%=tmp.getProperty_name() %></td>
					<td><%=tmp.getProperty_value() %></td>
					<td><%=tmp.getDescription() %></td>
				</tr>
				<%
			}
		%>
	</tbody>
</table>
</body>
</html>









