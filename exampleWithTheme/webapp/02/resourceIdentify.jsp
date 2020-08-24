<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/resourceIdentify.jsp</title>
</head>
<body>
<h4>자원 식별</h4>
<pre>
	URI(Uniform resource Identifier)
	1. URL(Locator) : root 를 기준으로 경로를 설정하고 자원을 식별.
	2. URN(Name) : 자원에 명명규칙 적용.
	3. URC(Content) : 자원의 속성을 통해 식별.
	
	URL 표현 방법
	1. 절대 경로 : 루트부터 모든 경로를 표기
		1) client side : 도메인이나 IP 이후의 컨텍스트명부터 모든 경로를 표기
		   ex) /webStudy01/images/prod-1.jpg
		2) server side : context root 이후의 경로를 표기
		   ex) /images/prod-1.jpg
		<%
// 			ServletContext ctx = getServletContext();
// 	 		out.println( application == ctx);
			String realPath = application.getRealPath("/images/prod-1.jpg");
			out.println(realPath);		    
		%>
	2. 상대 경로 : 현재 위치를 기준으로 상대적으로 경로를 표기.
</pre>
<img src="http://localhost/sample/images/prod-1.jpg" />
<img src="<%=request.getContextPath() %>/images/prod-1.jpg" />
<img src="../images/prod-1.jpg" />
</body>
</html>












