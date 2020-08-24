<%@page import="java.util.List"%>

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
			
			<tr>
				<td>${selectStu.code}</td>
				<td>${selectStu.name}</td>
				<td>${selectStu.birthday}</td>
				<td>${selectStu.age}</td>
				<td>${selectStu.gender}</td>
				<td>${selectStu.grade}</td>
				<td>${selectStu.license}</td>
			</tr>
		</tbody>
	</table>
	<br>
	<form method="post" name="delForm" action="${pageContext.request.contextPath}/ddit/studentDelete.do">
		<input type="hidden" name="code" value="${selectStu.code}">
	</form>
	<a
		href="${pageContext.request.contextPath}/ddit/studentUpdate.do?code=${selectStu.code}"
		style="color: red; text-decoration: none; font-size: 15pt;">수정</a>
		
	<a href="#"
		style="color: green; text-decoration: none; font-size: 15pt;"
		id="delBtn" onclick="if(confirm('삭제?'))document.delForm.submit();">삭제</a>
		
	<a href="${pageContext.request.contextPath}/ddit/dditStudents.do"
		style="color: blue; text-decoration: none; font-size: 15pt;">목록</a>
</body>
</html>