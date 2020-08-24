<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>07/viewCookie.jsp/0518</title>
	</head>
	<body>
	<h4> 동일한 경로에서 쿠키 확인하기 07폴더 </h4>
		<pre>
			<%
			Cookie[] cookies4 = request.getCookies();
			if(cookies4!=null){
				for(Cookie tmp : cookies4){
					String name = tmp.getName();
					String value2 = URLDecoder.decode(tmp.getValue(),"UTF-8");
					out.println(String.format("%s : %s ", name, value2));
				}
			}	
			%>
		</pre>
	</body>
</html>