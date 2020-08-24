<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%--@ page language="java"
    pageEncoding="UTF-8"--%>
   <%--
   		response.setHeader("Content-Type", "text/plain");
   --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>04/respinseDesc.jsp/0512</title>
	</head>
	<body>
		<h5 style="color:red">HttpServletResponse (http response)</h5>
		<pre>
			: 서버에서 클라이언트 쪽으로 전송되는 응답에 대한 모든 정보를 가진 객체 
			( request vs. response 1:1 )
			
			: http의 응답 패키징 방식 
				1. Response Line : Protocol(version), Status Code 
					Status Code(응답상태 코드) > 
						100 ~ : ING... (진행중), 'WebSoket' 때문에 생겨난 상태코드 
						200 ~ : OK
						300 ~ : 클라이언트의 추가적인 요청이 필요한 상황
							전에 한번 호출했던 이미지를 다시 호출할 떄 200 -> 코드에서 304코드로 변하는 걸 알 수 있다.
							1) 304 : Not Modified
							2) 302/307/301 : Moved , Location Header와 함께 사용된다. , Redirect 구조(**) ,  
						400 ~ : Client side Fail
							1) 400 : Bad Request , 잘못된 요청 , 요청을 처리하기 위한 파라미터의 값이 null이거나 type, 크기가 맞지 않을 경우 
							2) 404 : Not Found , 주소가 잘못된 경우 
							3) 403 : Forbidden , 인가처리가 잘못되어 있다 , 클라이언트에게 줄수없는 데이터를 요청 할 때 , 보안
							4) 401 : Unauthorized , 허가되지 않은 데이터 , 보안
						500 ~ : Server side Fail , 서버에서 어떤 문제가 발생했는지 클라이언트에게 알려지면 안되지만 서버에서 어떤 문제가 발생했는지 알려지면 안되니까 세분화되지 않았다.
						response.sendError(status_code)
						<%--
							if(1==1) throw new RuntimeException("강제 발생 예외");
						--%>
				2. Response Header : body에 있는 컨텐츠에 대한 부가정보 (meta data)
					- Content-Type : 
					- Content-Length : 서버가 감당할수 없는 길이라면 데이터를 쪼개서 받아온다.
					- Cache-Control : 
					- Pragma : 
					- Expires : 
					- Location : (제일중요!)
					
					1) 응답데이터의 MIME 설정 : Content-Type
						response.setHeader("Content-Type","text/html;charset=UTF-8") >> 정석
						response.setContentType("text/html;charset=UTF-8")
						jsp에서 page 지시자의 contentType 속성
					
					2) 캐시제어 : Content-Content, Pragma, Expires
						<a href="cacheControl.jsp">cacheControl.jsp 참고</a>
					
					3) auto request : Refresh
						<a href="autoRequest.jsp">autoRequest.jsp 참고</a>
					
					4) 흐름 제어 : Location 
					response.setHeader(name, value)
					 주의 ! value의 타입은 문자열이고, 특수문자가 포함되면 URLEncoding 이 필요하다.
						<a href="flowControl.jsp">flowControl.jsp 참고</a>
					<%
						// 인코딩을 직접해야하므로 특수문자에 주의하도록 한다. 
						response.setHeader("", "한글");
					%>
				3. Response Body(Content Body, Message Body) : 실제 응답 데이터 영역 
					- 데이터 저장  
						response.getWriter, response.getOutputStream, out(JSPWriter), 정적테스트
		</pre>
		<img src="<%=request.getContextPath() %>/images/cat.jpg">
		<p> 페이지 로딩 요청, 이미지 요청 , 총 두번의 요청이 필요하다 </p>
	</body>
</html>