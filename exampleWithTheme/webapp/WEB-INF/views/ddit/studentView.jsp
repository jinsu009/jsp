<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 한명의 학생의 모든 정보를 UI로 구성. -->
<!-- 이름(name), 생년월일(birthday), 
나이(age), 학력(grade), 성별(gen), 자격증(license), 경력사항(career) -->
<table class="table table-bordered table-striped">
	<tr>
		<th>학번</th>
		<td>${student.code }</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${student.name }</td>
	</tr>
	<tr>
		<th>생년월일</th>
		<td>${student.birthday }</td>
	</tr>
	<tr>
		<th>나이</th>
		<td>${student.age }</td>
	</tr>
	<tr>
		<th>학력</th>
		<td>${student.grade }</td>
	</tr>
	<tr>
		<th>성별</th>
		<td>${student.gen eq "M" ?"남":"여" }</td>
	</tr>
	<tr>
		<th>자격증</th>
		<td>${student.license }</td>
	</tr>
	<tr>
		<th>경력사항</th>
		<td>${student.career }</td>
	</tr>
</table>
<form method="post" id="studentDelForm" name="delForm" action="${pageContext.request.contextPath }/ddit/studentDelete.do">
	<input type="hidden" name="code" value="${student.code }"> 
</form>
<a class="btn btn-primary byAjax mr-2" href="${pageContext.request.contextPath }/ddit/studentUpdate.do?code=${student.code }">수정</a>
<a class="btn btn-danger mr-2" href="#" onclick="if(confirm('삭제할래?')) $('#studentDelForm').submit();">삭제</a>
<a class="btn btn-secondary byAjax" href="${pageContext.request.contextPath }/ddit/dditStudents.do">목록으로</a>














