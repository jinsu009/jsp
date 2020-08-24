<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>06/action.jsp/0515</title>
	</head>
	<body>
	
		<h4>Action Tag</h4>
		<pre>
			§ jsp 스펙에서 기본 제공되는 일종의 커스텀 태그 
			커스텀 태그의 사용 : &lt;prefix:tagname&gt;  --> JSTL

			customtag 장점 )
				1. 가독성 
				2. 좀 더 쉽게 자바 코드를 작성
			
			흐름제어용 액션 태그 
			serverside 경로 를 작성해주는 이유는 jsp:forward가 server 태그이기 때문이다. 
<%-- 			<jsp:forward page="/01/standard.jsp"/>--%>
<%-- 			<jsp:include page="/06/fragment.jsp"/> --%> -- 동적 

			실행하기 전에는 webpage가 없으므로 file을 불러온다. 
<%-- 			<%@ include file="/06/fragment.jsp" %>  --%> -- 정적
			
			<%--
				pageContext.include("/06/fragment.jsp"); //동적
			
			--%>
			
			<%--=includeeVariable --%> -- 정적일때만 소스를 불러와서 불러오는 jsp에 저장된 변수를 가져올수있다
			
			§ include의 종류  ( 분류 기준 : 시점과 대상에 따른 구분 )
				1. 정적 include : 실행 이전에 jsp에 해당하는 서블릿 소스를 파싱하는 단계.
								소스를 include 한다.
								1) include 지시자 사용 : jsp파일 하나에 대한 include
								2) web.xml 사용 : context전체를 대상으로 한 include
				2. 동적 include : Runtime에 실행결과 (data)를 include.  
								1) RequestDispatcher 사용
								2) pageContext 의 include
								3) jsp include action tag
								
								* 동적 include로 페이지 모듈화 
			
		</pre>
	</body>
</html>