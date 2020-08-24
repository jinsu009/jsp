<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	response.setHeader("Content-Type", "text/plain");
--%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/responseDesc.jsp</title>
</head>
<body>
<h4>HttpServletResponse (http response) </h4>
<pre>
	: 서버에서 클라이언트쪽으로 전송되는 응답에 대한 모든 정보를 가진 객체. (request vs response 1:1)
	http 의 응답 패키징 방식
	1. Response Line : Protocol(version), Status Code
		Status Code (응답상태코드)
		100~ : ING....
		200~ : OK
		300~ : 클라이언트의 추가 액션이 필요한 상황.
				304(Not Modified), 302/307(Moved, Location 헤더와 함께 사용-->Redirect 구조)
		400~ : Client side Fail
				400(Bad request), 404(Not Found), 403(Forbidden), 401(Unauthorized)
		500~ : Server side Fail
		response.sendError(status_code)
		<%--
			if(1==1) throw new RuntimeException("강제 발생 예외");
		--%>
	2. Response Header : body 에 있는 컨텐츠에 대한 부가정보(meta data)
		: Content-Type, Content-Length, Cache-Control, Pragma, Expires, Refresh, Location
		1) 응답 MIME 설정 : Content-Type
		   response.setHeader("Content-Type", "text/html;charset=UTF-8")
		   response.setContentType("text/html;charset=UTF-8")
		   jsp 의 page 지사자의 contentType 속성.
		2) 캐시 제어 : Cache-Control, Pragma, Expires
			<a href="cacheControl.jsp">cacheControl.jsp 참고</a>
		3) auto request : Refresh
			<a href="autoRequest.jsp">autoRequest.jsp 참고</a>
		4) 흐름 제어 : Location
			<a href="flowControl.jsp">flowControl.jsp 참고</a>
		response.setHeader(name, value)
		주의! value 의 타입은 문자열이고, 특수문자가 포함되면, URLEncoding 이 필요함.
	3. Response Body(Content Body, Message Body) : 실제 응답 데이터 영역
		response.getWriter, response.getOutputStream, out(JSPWriter), 정적텍스트
</pre>
<img src="<%=request.getContextPath() %>/images/prod-1.jpg" />
</body>
</html>





