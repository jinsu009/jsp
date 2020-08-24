<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
inner.jsp 대신 요청을 받기위한 컴포넌트.
inner.jsp 로 이동???
<%
// 	response.sendRedirect(request.getContextPath() + "/WEB-INF/views/inner.jsp");
	request.getRequestDispatcher("/WEB-INF/views/inner.jsp").forward(request, response);
%>
</body>
</html>











