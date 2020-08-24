<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>04/outer.jsp/0512</title>
	</head>
	<body>
	<pre>
		inner.jsp를 대신해서 요청을 받기 위한 컴포넌트
		이 때 사용할수 있는 이동방식 dipatch(o), redirect(x) 	
	</pre>
	<p>redirect</p>
	<%
// 		response.sendRedirect(request.getContextPath()+"/WEB-INF/views/inner.jsp");
	%>
	<p>dispatch</p>
	<%
		request.getRequestDispatcher("/WEB-INF/views/inner.jsp").forward(request, response);
	%>
	</body>
</html>