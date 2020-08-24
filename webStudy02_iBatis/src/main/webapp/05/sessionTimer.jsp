<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>05/sessionTimer.jsp/0513</title>
		<script
		  src="https://code.jquery.com/jquery-3.5.1.min.js"
		  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		  crossorigin="anonymous"></script>
	</head>
	<body>
		<h2 style="color:pink;"> 인터넷 뱅킹 timer 구현</h2>
		<!--
			1. 최초로 만료시간을 4:00의 형태로 출력
			2. 매 1초마다 타이머 discount 
			3. 잔여시간이 1분이 되면 메세지 출력
			4. 연장 선택 > 메세지 창 없애고 타이머 초기화 시키기 
			5. 아니오 > 메세지 창 없애기 
			6. 0초가 되면 모든 작업 중단  
		 -->
	
		<div id="sessionTimer">
<!-- 			<div id="timerArea"></div> -->
			<div id="msgArea">
				세션을 연장하시겠습니까?
				<input type="button" value="yes" class="msgBtn" id="yesBtn"/>
				<input type="button" value="no" class="msgBtn" id="noBtn"/>
			</div>
		</div>
		<%
			session.setMaxInactiveInterval(2*33); // session timeout 시간을 set 
		%>
		<!-- 스티브사우더스의 웹사이트 최적화 원칙 -->
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/sessionTimer.js"></script>
		<script type="text/javascript">
			$("#sessionTimer").sessionTimer(<%=session.getMaxInactiveInterval()%>);
		</script>
	</body>
</html>