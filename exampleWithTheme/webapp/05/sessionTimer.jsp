<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/sessionTimer.jsp</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
</head>
<body>
<!-- 인터넷 뱅킹 타이머 구현 -->
<div id="sessionTimer">
	<!-- 1. 최초로 만료시간을 4:00의 형태로 출력 -->
	<!-- 2. 매1초마다 타이머 discount -->
	<!-- 3. 잔여시간 1분이 되면, 메시지 출력 -->
	<!-- 4. 연장 선택시? 메시지 창 없애고, 타이머 초기화 -->
	<!-- 5. 아니오? 메시지 창 없애기. -->
	<!-- 6. 0초가 되면? 모든 작업 중단. -->
	<div id="msgArea">
		세션을 연장하시겠습니까?
		<input type="button" value="예" class="msgBtn" id="yesBtn"/>
		<input type="button" value="아니오" class="msgBtn" id="noBtn"/>
	</div>
</div>
<%
	session.setMaxInactiveInterval(2*60);
%>
<!-- 스티브 사우더스의 웹사이트 최적화 원칙  -->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/sessionTimer.js"></script>
<script type="text/javascript">
	$("#sessionTimer").sessionTimer(<%=session.getMaxInactiveInterval() %>);
</script>
</body>
</html>








