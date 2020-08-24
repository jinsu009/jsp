<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>04/flowControl.jsp/0512</title>
	</head>
	<body>
		<h3 style="color:orange;">웹 어플리케이션의 흐름 제어(A -> B)</h3>
		<pre>
			** 두 개이상의 servlet, jsp 혹은 하나의 요청에 servlet과 jsp가 같이 동작하는 것
			Http 특성 : Connectless,  stateless : 비연결지향 무상태 특성 
			
			1. Request Dispatch : 최초로 발생한 기존의 요청정보를 이동과정에서 그대로 분기 
				1) Forward : 최초의 요청이 A로 발생하고 , A에서 요청을 분석, 
							 B로 이동후 최동 응답 UI가 전송된다.  
				2) Include : B로 이동할 때는 동일한 방식이나, 
							 B에서 처리가 완료되면, A로 회귀한 후 최종 UI 전송(A+B)
							 ! 동적 Include 처리 !
							 페이지 모듈화에서 주로 사용 
				* 클라이언트는 B.jsp의 존재를 알지 못한다
				<%
// 					calForm.jsp로 이동 
// 					1) Forward : 분기제어 관리자 , server side path 작성 
// 					RequestDispatcher rd = request.getRequestDispatcher("/01/calForm.jsp");
// 					rd.forward(request, response);
// 					2) Include
// 					rd.include(request, response);
				%>

			2. Redirect : 이동하는 과정에서 body가 없는 응답이 전송 됨
				최초의 요청이 A로 발생하고, body는 없으나, 
				302/307 상태 코드와 location 헤더를 가진 응답이 전송 
				location(B의 주소) 헤더의 방향으로 새로운 요청이 발생 (B), -- location은 클라이언트 측에서 사용하게 된다. 
				최종 응답 UI가 B에서 전송 됨
				Post-Redirect-Get 패턴에서 주로 사용된다 (예.회원가입)
				
				* 클라이언트는 B.jsp에게 새로운 요청을 전송하게 된다. 
				<%
// 					3)
					// location : 현재 자원의 위치 이면서 새로운 자원의 위치, contextpath경로를 작성해줘야 한다.
					response.sendRedirect(request.getContextPath() + "/01/calForm.jsp");
				
				%>
				* location header : 새로운 자원의 위치가 나가야한다.   
		</pre>
	</body>
</html>