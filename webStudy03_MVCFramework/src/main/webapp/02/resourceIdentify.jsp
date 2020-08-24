<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>02/resourceIdentify.jsp/20200508</title>
	</head>
	<body>
		<h3>자원 식별</h3>
		<pre>
			URI(Uniform resource Identifier) : 목적 : 식별! 
				1. url(- Locator) : root를 기준으로 해서 경로를 설정하고 자원을 식별하는 방법  
				2. urn(- Name) : 자원에 명명규칙을 적용 
				3. urc(- Content) : 자원의 속성을 통해서 식별 ,, 예를 들어 이외수라는 작가의 도서를 찾을때 이외수 작품이 하나가 아니기때문에 식별할 수 없다. 
				
			---
			URL 표현방법 
			1. 절대 경로 : 루트부터 모든 경로를 표기,  경로를 어디서 사용하느냐에 따라 client와 server로 나뉜다.  
				1) client side : 도메인이나 ip이후의 컨텍스트 이름 부터 모든 경로를 표기 
					예) /webStudy01/images/cat.jpg
				2) server side : (클라이언트와 서버의 시작위치가 다르다), context root 이후의 경로를 표기   
					예) /images/cat.jpg 
					
					<%
// 						ServletContext ctx = getServletContext();
// 						out.println(application == ctx); // 결과 : true

						String realPath = application.getRealPath("/images/cat.jpg");
						out.println(realPath);
					%>
			2. 상대 경로  : 현재 위치를 기준으로 상대적으로 경로를 표기 
		</pre>
<!-- 			<img src="../images/cat.jpg"/> -->

	<h1>절대경로 표현방식 : root가 없음에도 불구하고 이미지는 잘 나온다.</h1>
<!-- 			<img src="http://localhost/webStudy01/images/Jellyfish.jpg"/> -->
<!-- 			<img src="/webStudy01/images/cat.jpg"/> -->

			<img src="http://localhost/webStudy01/images/Jellyfish.jpg"/>
			<img src="<%=request.getContextPath() %>/images/cat.jpg"/>
	
	<h1>상대경로</h1>
			<img src="../images/cat.jpg"/>
	</body>
</html>