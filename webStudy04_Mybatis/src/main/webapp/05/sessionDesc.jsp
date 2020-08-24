<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>05/session.jsp/0513</title>
	</head>
	<body>
		<h4 style="color:teal;"> HttpSession : Session</h4>
		<pre>
			시간의 의미, connectless : web > 한 클라이언트가 하나의 브라우저를 이용해서 서버 어플리케이션을 사용하고 있는 동안.
				- 세션의 시작 : 최초의 요청 발생 
					<span style="color:green;">sessionID(16진수) : <%=session.getId() %></span>
					<a href="sessionDesc.jsp;jsessionid=<%=session.getId() %>">세션유지</a>
					<%--
						session.invalidate();
					--%>
					<span style="color:green;">session 생성 시점 : <%=new Date(session.getCreationTime()) %></span>
					<span style="color:green;">현재 session 마지막 요청 시간  : <%=new Date(session.getLastAccessedTime()) %></span>
					<span style="color:green;">session timeout : <%=session.getMaxInactiveInterval() %> = 30분</span>
					* 파이어폭스와 크롬은 다른 session으로 인식된다. 
					
					session 만료시간을 임의로 설정해보자 
					<%
						session.setMaxInactiveInterval(4*60);
					
					%>
					<span style="color:purple;">session timeout :<%=session.getMaxInactiveInterval() %></span>
				
				- 세션 유지 방법 (tracking-mode)
					1) Cookie : session id를 jsessionId와 같은 쿠키의 형태로 클라이언트에 저장
					2) URL : jsessionId와 같은 세션 파라미터 형태로 세션을 전송(request line : 누구나 볼 수 있다, 보안에 취약 )
					3) SSL : 세션을 유지하기 위한 정보를 secure layer를 통해 전송 (https를 인증서가 없으면 사용불가)
					
				- 세션의 종료 : 
					1) timeOut : 일정 만료시간을 설정하고 , 만료시간 이내에 새로운 요쳥이 발생하지 않으면 session 종료 
					2) 쿠키 삭제 : 
					3) 브라우저의 종료 : 
					4) 로그아웃 : (session 만료) , invalidate() 를 사용해서 session을 재설정한다. 
				- session ID : 클라이언트가 접속할때마다 생성되고 id로 클라이언트를 식별할 수 있다
			통로의 의미 : DB > 서버와 클라이언트 사이에 유일하게 개방된 통로. 
		</pre>
	</body>
</html>