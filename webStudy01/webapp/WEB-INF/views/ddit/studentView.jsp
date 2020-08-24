<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.DDITStudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ddit/studentView.jsp/0520</title>
</head>
<body>
	<h3>한명의 학생의 모든 정보를 UI로 구성</h3>
	<table border="1">
		<thead>
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>생일</th>
				<th>나이</th>
				<th>성별</th>
				<th>학교</th>
				<th>자격증</th>
			</tr>
		</thead>
		<tbody>
			<%
			DDITStudentVO dvo = (DDITStudentVO)request.getAttribute("selectStu");
		
			%>
			<tr>
				<td><%=dvo.getCode() %></td>
				<td><%=dvo.getName() %></td>
				<td><%=dvo.getBirthday() %></td>
				<td><%=dvo.getAge() %></td>
				<td><%=dvo.getGender() %></td>
				<td><%=dvo.getGrade() %></td>
				<td><%=dvo.getLicense() %></td>
			</tr>
		</tbody>
	</table>
	<br>
	<form method="post" name="delForm" action="<%=request.getContextPath() %>/ddit/studentDelete.do">
		<input type="hidden" name="code" value="<%=dvo.getCode()%>">
	</form>
	<a
		href="<%=request.getContextPath() %>/ddit/studentUpdate.do?code=<%=dvo.getCode() %>"
		style="color: red; text-decoration: none; font-size: 15pt;">수정</a>
		
	<a href="#"
		style="color: green; text-decoration: none; font-size: 15pt;"
		id="delBtn" onclick="if(confirm('삭제?'))document.delForm.submit();">삭제</a>
		
	<a href="<%=request.getContextPath() %>/ddit/dditStudents.do"
		style="color: blue; text-decoration: none; font-size: 15pt;">목록</a>
</body>
</html>