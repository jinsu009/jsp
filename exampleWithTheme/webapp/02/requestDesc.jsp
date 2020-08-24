<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/requestDesc.jsp</title>
</head>
<body>
<h4>HttpServletRequest(요청)</h4>
<pre>
	HttpServletRequest req : 클라이언트의 요청 정보를 캡슐화한 객체;
	HTTP 에 의해 요청이 패키징 되는 구조
	1. Request Line : URL, Protocol(version), Http Method
	   Http Method : 요청의 목적/방법(수단)
	   1) GET (R) : 조회, Body 없음.
	   2) POST(C) : 등록, Body 존재.
	   3) PUT (U) : 수정, form 이 put 을 지원하지 않을때, method="post" 로 설정,
	                     "_method" name 의 hidden 태그 생성.
	   4) DELETE (D) : 삭제
	   5) Headers :  차후 응답이 전송될때, body가 없음.
	   6) Options : preflight 요청에 주로 사용.
	   7) Trace :  server debugging
	   	   
	2. Request Header : 클라이언트와 요청에 대한 부가 정보(meta data)
	3. Request Body(Content Body, Message Body) : 클라이언트의 명시적인 전송 컨텐츠(메시지)
	
	요청 파라미터 전송 구조
	1. POST : Body 를 통해 전송
				request.setCharacterEncoding(encoding);
				- 요청의 body 영역내에 있는 특수문자에 대한 디코딩 방식 결정.	
	2. GET : Line 을 통해 queryString 의 형태로 전송.
	   URL 에 queryString 의 사용되는 형식
	     주소?queryString
	       sector1&sector2...&sectorN
	       					  param_name=param_value	
	       	ex) server.xml -> http connector URIEncoding 설정으로 서버의 주소 해석 방법을 변경.
	       					useBodyEncodingForURI 설정은 	
	       					setCharacterEncoding 메소드를 GET 방식에서 사용.		  				
	<a href="<%=request.getContextPath() %>/02/factorial.jsp?num=5">5!연산수행</a>
	
	서버에서 파라미터 확보
	1. String getParameter(name)
	2. String[] getParameterValues(name) : 하나의 파라미터명으로 여러 값이 전달될때.
	3. Map&lt;String,String[]&gt; getParameterMap()
	4. Enumeration&lt;String&gt; getParameterNames() : 모든 파라미터명을 확보.
</pre>
</body>
</html>












