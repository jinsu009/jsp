<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/sessionDesc.jsp</title>
</head>
<body>
<h4> HttpSession session </h4>
<pre>
	 세션??
	 	(시간, 통로)
	 	시간 (웹) , connectless : 한클라이언트가 하나의 브라우저를 이용해서 
	 							서버 어플리케이션 사용하고 있는 동안.
	 		세션의 시작 : 최초 요청 발생시, 세션 아이디 부여
	 		세션 아이디 : <%=session.getId() %>
	 		<a href="sessionDesc.jsp;jsessionid=<%=session.getId() %>">세션 유지</a>
 	 		세션 생성 시점 : <%=new Date(session.getCreationTime()) %>
 	 		현재 세션의 마지막 요청 시간 : <%=new Date(session.getLastAccessedTime()) %>
 	 		세션 timeout : <%=session.getMaxInactiveInterval() %>s
 	 		<%
 	 			session.setMaxInactiveInterval(4*60);
 	 		%>
 	 		세션 timeout : <%=session.getMaxInactiveInterval() %>s
	 		<%--
	 			session.invalidate();
	 		--%>
	 		세션 유지 방법(tracking-mode)
	 		1) Cookie : 세션 아이디를 JSESSIONID 와 같은 쿠키의 형태로 클라이언트에 저장.
	 		2) URL : jsessionid 와 같은 세션 파라미터의 형태로 세션을 전송(request line).
	 		3) SSL : 세션을 유지하기 위한 정보를 secure layer 를 통해 전송.
	 		세션의 종료 
	 		 1) timeout, 일정 만료 시간을 설정하고, 만료 시간 이내에 새로운 요청이 발생하지 않으면.
	 		 2) 쿠키 삭제
	 		 3) 브라우저 종료					
	 		 4) 로그아웃
	 	통로 (DB) : 서버와 클라이언트 사이에 유일하게 개방된 통로
</pre>
</body>
</html>








