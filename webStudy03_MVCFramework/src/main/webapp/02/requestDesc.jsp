<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>02/requestDesc.jsp/20200508</title>
	</head>
	<body>
		<h3>HttpServletRequest(요청)</h3>
		<pre>
			§ HttpServletRequest request : 클라이언트의 요청 정보를 캡슐화 한 객체; 
			§ Http에 의해 요청이 패키징 되는 구조 
			
			1. Request Line : URL, Protocol(version), Http Method
				Http Method(7가지 종류 중 일반적으로 4가지만 사용)
				* 요청의 목적, 방법(수단),  
				1) get(C) : 조회, body가 만들어지지 않는다. 
				2) post(R) : 등록, body 존재. 
				3) put(U) : 수정, body 존재.
				4) delete(D) : 삭제, 어떤 데이터를 삭제할 것인지 정보가 필요하므로 body가 필요하다 .
					* 원칙적으로 form태그는 put과 delete방식을 지원하지 않는다. 
					* put과 delete를 사용하기 위해서는 post방식으로 설정한 뒤 
					* "_method" name을 가지고 있는 hidden 태그로 작성하고 value로 post를 준다.
				
				5) Headers : 차후 응답이 전송 될 때, body가 없다. 
				6) Options : preflight요청(사전요청)에 주로 사용(서버에따라 지원되는지 알아보기 위해서 사용)
				7) Trace : 서버를 디버깅 하는 목적 (server debugging)
				
			2. Request Header : 클라이언트와 요청에 대한 부가 정보(meta data)
			3. Request Body(Content Body, Message Body) : 클라이언트의 명시적인 전송 컨텐츠(메세지)
			
			§ 요청 파리미터 전송 구조 
			1. POST : Body를 통해 전송
						request.setCharacterEncoding(encodig);
						- 요청의 body 영역내에 있는 특수문자에 대한 디코딩 방식 결정 
			2. GET : line을 통해 queryString의 형태로 전송
				URL에 QueryString의 사용되는 형식 : 주소?queryString
				queryString 형식 : sector1&sector2...&sectorN
				sector 형식 : param_name = param_value
				예) server.xml > http connector URIEncoding 설정으로 서버의 주소 해석 방법을 변경
								useBodyEncodingForURI 설정은 setCharacterEncoding 메소드를 get방식에서 사용 
			==============================================
<%-- 			<a href="<%=request.getContextPath() %>/02/factorial.jsp?num=5">5!연산수행</a> --%>
			<a href="?num=5">5!연산수행</a>
			===============================================
			한글이 깨지면 데이터 전달방식을 확인 : post, get 
			
			§ 서버에서 파라미터 확보 
				1. String getParameter(name)
				2. String[] getParameterValues(name) : 하나의 파라미터 명으로 여러값이 전달될 때
				3. Map&lt;String, String[]&gt; getParameterMap()
				4. Enumeration&lt;String&gt; getParameterNames() : 모든 파라미터명을 확보 
			
		</pre>
	</body>
</html>