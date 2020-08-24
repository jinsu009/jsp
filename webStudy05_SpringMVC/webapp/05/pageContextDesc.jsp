<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error/errorLocal.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>05/pageContextDesc.jsp/0514</title>
	</head>
	<body>
		<h3 style="color:navy;">PageContext</h3>
		<pre>
			§ 해당 jsp 페이지에 대한 모든 정보를 캡슐화 하고 있는 객체, 모든 객체 중 가장 먼저 생성된다. 
			 1. 나머지 기본객체 확보 (★★★★★★)
			 	request와 pageContext.getRequest()은 같은 객체 이다.
			 	<%=request == pageContext.getRequest() %>
			 	<%=application == pageContext.getServletContext() %>
			 	<%=session == pageContext.getSession() %>
			2. 페이지 흐름 제어  : forward, include
			<%
				// b.jsp의 주소
// 				pageContext.forward("/05/applicationDesc.jsp");
// 				request.getRequestDispatcher("/05/applicationDesc.jsp").forward(request, response);

				// include 가 불려지는 위치가 다르다. // 페이지 모듈화
// 				pageContext.include("/05/applicationDesc.jsp");
// 				request.getRequestDispatcher("/05/applicationDesc.jsp").include(request, response);
			%>
			인클루드 이후의 UI
			
			3. 에러데이터 확보하기 : /error/errorLocal.jsp
				<%
				// 고의적인 error 코드 
// 					if(1==1) throw new IOException("강제 발생 예외");	
					ErrorData ed = pageContext.getErrorData();
					String requestURI = ed.getRequestURI();
					Throwable e = ed.getThrowable();
					int sc = ed.getStatusCode();
				%>
				requestURI : <%=requestURI %>
				Throwable : <%=e%>
				getStatusCode : <%=sc %>
				
				* error이 발생하지 않으면 null 
			4. Scope의 Attribute 확보 (★★★★★)
			<a href="scopeDesc.jsp">scopeDesc.jsp</a>
		</pre>
	</body>
</html>