<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/implicitObject.jsp</title>
</head>
<body>
<h4> 기본 객체(내장 객체, 묵시적 객체) </h4>
<pre>
	1. request(HttpServletRequest) : 요청 정보 캡슐화
	2. reponse(HttpServletResponse) : 응답 정보 캡슐화
	3. out(JspWriter) : 응답 버퍼에 기록하기 위한 스트림 객체(body 기록, buffer 제어).
	4. session(HttpSession) : 한사람의 사용자가 하나의 브라우저를 사용하고 있는 동안(세션)!!의 상태 정보 캡슐화
	5. application(ServletContext) : servlet container 와 하나의 context 에 대한 정보 캡슐화.
	<%=application.hashCode() %>, <%=getServletContext().hashCode() %>	
	6. config(ServletConfig) : 서블릿의 메타 정보 캡슐화
	7. page(Object) : 현재 jsp 페이지의 인스턴스에 대한 참조(==this).
	8. exception(Throwable) : 발생한 예외에 대한 정보 캡슐화  <% %>
	9(*****). pageContext(PageContext) : 모든 객체중 가장 먼저 생성, 나머지 기본 객체에 대한 getter 가짐.
</pre>
</body>
</html>















