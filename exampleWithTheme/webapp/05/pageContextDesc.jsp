<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error/errorLocal.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/pageContextDesc.jsp</title>
</head>
<body>
<h4> PageContext pageContext</h4>
<pre>
	: 해당 JSP 페이지에 대한 모든 정보를 캡슐화하고 있는 객체로, 모든 객체중 가장 먼저 생성됨.
	1. 나머지 기본 객체 확보 (*****)
	<%=request == pageContext.getRequest() %>
	<%=application == pageContext.getServletContext() %>
	<%=session == pageContext.getSession() %>
	2. 페이지 흐름 제어 : forward, include
	<%
// 		pageContext.forward("/05/applicationDesc.jsp");
// 		request.getRequestDispatcher("/05/applicationDesc.jsp")
// 				.forward(request, response);
// 		pageContext.include("/05/applicationDesc.jsp"); // 페이지 모듈화
// 		request.getRequestDispatcher("/05/applicationDesc.jsp")
// 				.include(request, response);
	%>
	3. 에러 데이터 확보하기
		<% 
			if(1==1) throw new IOException("강제 발생 예외");
			ErrorData ed = pageContext.getErrorData();
			String requestURI = ed.getRequestURI();
			Throwable e= ed.getThrowable();
			int sc = ed.getStatusCode();
		%>
		<%=requestURI %>
		<%=e %>
		<%=sc %>
	4. Scope의 Attribute 확보(*****)	
	인클루드 이후의 UI
</pre>
</body>
</html>
















