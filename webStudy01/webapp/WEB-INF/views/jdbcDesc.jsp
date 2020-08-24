<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.*"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>08/jdbcDesc.jsp/0519</title>
	</head>
	<body>
		<h3 style="color:pink">JDBC(Java DataBase Connectivity)</h3>
		<pre>
			1. 벤더가 제공하는 드라이버를 찾아서 build path에 추가 
			2. ClassLoader를 통해 드라이버 클래스 로딩
			3. connection 생성
			4. Query object 생성 
				1) Statement : 동적쿼리 (쿼리를 실시간으로 변경가능 / sql Injection 발생)
				2) PreparedStatement : 쿼리 객체 생성 시 미리 컴파일된 쿼리 객체, 쿼리 파라미터 기호(?, literal) 
				3) CallableStatement : 프로시저나 function을 호출할 때 사용하는 쿼리 객체 
			5. Query 실행 : DML(데이터 조작어(select, delete, insert, update)), DCL(데이터 제어문,권한부여(grant, revoke)), TCL(commit, rollback), DDL(데이터 생성문, creat)
				ResultSet executeQuery() : select
				int executeUpdate() : insert , update, delete
			6. ResultSet 핸들링 
			7. 자원 Release (connection, statement, ResultSet)
				release는 resultset과 순서가 반대로 수행된다. 
				try문에 감싸지면 알아서 close된다. 
		</pre>
		<table border="1">
			<thead>
			<tr>
			<%
			Map<String, Object> modelMap =(Map)request.getAttribute("modelMap");
			String[] headers =(String[]) modelMap.get("headers");
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
				for(DataBasePropertyVO tmp :propList){
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