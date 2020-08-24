<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>04/cacheControl.jsp/0512</title>
	</head>
	<body>
		<h3 style="color:pink">캐시 제어</h3>
		<pre>
			Cache : 두 피어 사이에서 데이터가 전송 될 때 발생하는 속도 차이를 보완하기 위해 사용되는 임시 데이터 
			모든 브라우저는 자신만의 캐시 저장공간이 존재한다.  
		
			캐시를 제어할수 있는 헤더의 종류 
				1) Pragma : Http 1.0
				2) Cache-Control : Http 1.1
				3) Expires : 캐시데이터 만료기간을 설정할 때 사용 , 버전 공통 
			
			웹 표준화 환경 : 클라이언트의 시스템 버전에 상관없이 서비스를 제공해줘야 한다. 그러므로 세가지 헤더의 종류를 모두 사용해야한다.
			
			firefox 에서는 no store를 생성한다.
			<%
				response.setHeader("Pragma","no-cache"); // 1.0 
				response.setHeader("Cache-Control","no-cache"); // 1.1
				response.addHeader("Cache-Control","no-store"); // Firefox
				response.setDateHeader("Expires", 0);
				// data : long type  
				// 0 : 70년 0분 0초 > 캐시를 남기지 말라는 의미 
			%>
			캐시를 남겨서는 안될 때 :  거래내역 
		</pre>
	</body>
</html>