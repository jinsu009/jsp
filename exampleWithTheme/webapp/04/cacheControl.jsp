<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/cacheControl.jsp</title>
</head>
<body>
<h4>캐시 제어</h4>
<pre>
	Cache ?? 두 피어 사이에서 데이터가 전송될때 발생하는 속도 차이를 보완하기 위해 사용되는 임시 데이터.
	제어할수 있는 헤더의 종류
	1) Pragma : Http 1.0
	2) Cache-Control : Http 1.1
	3) Expires : 버전 공통, 캐시 만료 설정.
	<%
		response.setHeader("Pragma", "no-cache"); // 1.0
		response.setHeader("Cache-Control", "no-cache"); // 1.1
		response.addHeader("Cache-Control", "no-store"); // Firefox
		response.setDateHeader("Expires", 0);		
	%>
</pre>
</body>
</html>










