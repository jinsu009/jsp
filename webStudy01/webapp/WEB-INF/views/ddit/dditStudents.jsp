<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.DDITStudentVO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.servlet03.dao.DDITStudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ddit/dditStudents.jsp/0512</title>
		<style type="text/css">
			table{ column-span: collapse; }
			td, th { border:1px solid black; }
			.pink { background-color: pink }
		</style>
	</head>
	<body>
	
		<h3> 등록된 모든 학생 조회 </h3>
		
		<a href="<%=request.getContextPath() %>/ddit/regist.do">신규</a>
	
		<table>
			<thead>
				<tr>
					<th>학번</th>
					<th>이름</th>
					<th>생일</th>
					<th>나이</th>
					<th>성별</th>
					<th>학교</th>
					<th>경력</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<DDITStudentVO> allStudents = (List) request.getAttribute("allStudents");
// 					int b = allStudents.size()-1;
// 					String c = allStudents.get(b).getCode();
// 					String d = "";

					DDITStudentVO lastStudent = (DDITStudentVO)session.getAttribute("lastStudent");
					// session 은 필요없어지면 지워야 한다. .. 새로고침하면 class 적용이 사라짐 
					session.removeAttribute("lastStudent");

					
					for(DDITStudentVO vo : allStudents)
					{
						String clzName = vo.equals(lastStudent) ?  "pink" : "normal";
						
						// 가장 마지막에 추가된학생 배경색 넣어주기
// 						String a = vo.getCode();
// 						if(a.equals(c))	{ d="pink"; }
						%>
<%-- 							<tr class="<%=d %>"> --%>
							<tr class="<%=clzName %>">
								<td><%=vo.getCode() %></td>
								<td><a href="<%=request.getContextPath() %>/ddit/studentView.do?code=<%=vo.getCode()%>"><%=vo.getName() %></a></td>
								<td><%=vo.getBirthday() %></td>
								<td><%=vo.getAge() %></td>
								<td><%=vo.getGender() %></td>
								<td><%=vo.getGrade() %></td>
								<td><%=vo.getCareer() %></td>
							</tr>
						<%
					}
				%>
			</tbody>
		</table>
	</body>
</html>