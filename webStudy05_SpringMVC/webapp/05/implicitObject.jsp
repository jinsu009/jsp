<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
	<head>
	
		<meta charset="UTF-8">
		<title>05/implicitObject.jsp/0513</title>
	</head>
	<body>
		<h4 style="color:olive;">기본 객체 (내장객체, 묵시적 객체)</h4>
		<pre>
			- jsp 기본 객체 -
			1. request(HttpServletRequest) : 요청 정보 캡슐화 
			2. reponse(HttpServletResponse) : 응답 정보 캡슐화
			3. out(JspWriter) : 응답버퍼에 기록하기 위한 스트림 객체 (body 기록, buffer 제어) 
			4. session(HttpSession) : 한사람의 사용자가 하나의 브라우저를 사용하고 있는 동안 (session) 의 상태 정보 캡슐화
			5. application(ServletContext) : servlet container 와 하나의 context에 대한 정보 캡슐화 
				application.hashCode() :
						<span style="color:red"><%=application.hashCode()%></span>
				getServletContext().hashCode() :
						<span style="color:red"><%=getServletContext().hashCode() %></span>
			
			6. config(ServletConfig) : 서블릿의 메타 정보 캡슐화 , 
			7. page(Object) : 현재 jsp 페이지의 인스턴스에 대한 참조  *(==this)
				* this는 참조타입도 그대로 가져와서 형변환이 필요없다. page는 casting이 필요 
				* 잘사용하지 않는다 . 일반적으로 this만 사용 
			8. exception(Throwable) : 발생한 예외에 대한 정보 캡슐화
			<%	%>
			9. pageContext(PageContext) -- @중요@ : 모든 객체중 가장 먼저 생성된다 . 나머지 기본객체에 대한 getter를 가지고 있다. 
			
			---
			6,7,8 은 자주 사용 안함 
			1,2 는 많이 사용해왔음
			---
			( 05.13 ) 오늘은 3!
		</pre>
	
	</body>
</html>