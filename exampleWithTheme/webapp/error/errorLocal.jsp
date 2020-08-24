<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> 지역적 에러를 처리할 페이지 </h4>
<pre>
		<% 
			ErrorData ed = pageContext.getErrorData();
			String requestURI = ed.getRequestURI();
			Throwable e= ed.getThrowable();
			int sc = ed.getStatusCode();
		%>
		<%=requestURI %>
		<%=e %>, <%=exception %>
		<%=sc %>
</pre>		
</body>
</html>







