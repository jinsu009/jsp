<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/flowControl.jsp</title>
</head>
<body>
<h4> 웹 어플리케이션의 흐름 제어 (A->B)</h4>
<pre>
	Http 특성 : Connectless!! stateless!! (비연결지향 무상태특성)
	1. Request Dispatch : 최초로 발생한 기존의 요청정보를 이동과정에서 그대로 분기.
		1)  Forward : 최초의 요청이 A로 발생하고, A에서 요청을 분석하고, 
					  B로 이동 후 최종 응답 UI 가 전송됨.
					  Model2 구조에서 요청과 응답에 대한 처리자를 분리하는 구조.
		2)  Include : B 로 이동할때는 동일한 방식이나, 
		  			   B 에서 처리가 완료되면, A로 회귀한 후 최종 UI 전송(A+B)
		  			   동적 include 처리.
		  			   페이지 모듈화에서 주로 사용.
		 <%
// 		 	calForm.jsp 로 이동
// 		 	1) Forward
			RequestDispatcher rd = request.getRequestDispatcher("/01/calForm.jsp");
// 			rd.forward(request, response);
// 			2) Include
// 			rd.include(request, response);
		 %> 			   
	2. Redirect : 이동하는 과정에서 body가 없는 응답이 전송됨.
		최초의 요청이 A 로 발생하고, body 는 없으나, 
		302/307 상태 코드와 location 헤더를 가진 응답이 전송.
		location(B의 주소) 헤더의 방향으로 새로운 요청이 발생(B),
		최종 응답 UI 가 B에서 전송됨.
		Post-Redirect-Get(PRG)  패턴에서 주로 사용됨.
		<%
			response.sendRedirect(request.getContextPath() + "/01/calForm.jsp");
		%>
	
</pre>
</body>
</html>






