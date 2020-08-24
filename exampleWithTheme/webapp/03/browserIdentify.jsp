<%@page import="kr.or.ddit.enums.BrowserType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 사용자의 브라우저 종류를 식별. -->
<!-- "당신의 브라우저는 XXX 입니다." 메시지를 alert 창으로 출력. -->
<!-- explorer : 익플, chrome : 크롬, firefox : 파이어폭스,  other : 기타등등 -->
<%
	String agent = request.getHeader("User-Agent");
	String desc = BrowserType.browserIdentify(agent);
	String message = String.format("당신의 브라우저는 %s입니다.", desc);
%>
<script type="text/javascript">
	alert("<%=message %>");
</script>
</body>
</html>









