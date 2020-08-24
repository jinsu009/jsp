<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/eventProcess.jsp</title>
</head>
<body>
<h4 style="color:fuchsia;">서버 사이드 웹 어플리케이션의 이벤트 처리</h4>
<pre>
	1. 서버 이벤트 종류  ( 공통점 : 각각 자신의 scope를 가지고 있다. )
		1) request : 요청접수/응답 전송  (lifecycle), add/remove/replace 
		2) session : 세션생성/세션만료 (lifecycle), add/remove/replace 
		3) application : context loading / context unload (lifecycle), add/remove/replace
	
	2. 이벤트 처리 단계
		예. 누군가 버튼 클릭 > alert 창 띄우기
			- click 이벤트 
			- target button
			- action handler(function)
			- target > on/ onclick 부여
		-- client side 				- server side
		1) 이벤트 타겟 결정 (button) : server side application 자체
		2) 처리할 이벤트 결정 (click)
		3) 이벤트 핸들러 구현 (function) : Listener 구현 
		4) 타겟에 핸들러 부착(onclick, on) : web, xml에 부착, @WebListener
		
		** listner 잘 알기 
</pre>

</body>
</html>