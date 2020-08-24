<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/scopeDesc.jsp</title>
</head>
<body>
<h4> Scope (영역) </h4>
<pre>
	: 웹 어플리케이션에서 데이터를 공유할 목적으로 관리하는 저장 공간.
	: 생존 범위(사용범위)가 제한된 4개의 기본 객체가 관리하는 공간(Map).
	: scope를 통해 공유되는 데이터 : Attribute(name, value)
	1. page[Context] Scope : pageContext 에 의해 제어됨. 하나의 jsp 로 사용 범위 제한.
	2. request Scope : request 에 의해 제어됨. 해당 request 가 소멸되는 순간 공간의 사용 종료.
	3. session Scope : session 에 의해 제어됨. 해당 session 이내에서만 사용.
	4. application Scope : ServletContext 에 의해 제어됨.
	
	setAttribute(String name, Object value)
	getAttribute(name)
	removeAttribute(name)
	<%
		pageContext.setAttribute("pageScopeAttr", "페이지 속성");
		request.setAttribute("requestScopeAttr", "요청 속성");
		session.setAttribute("sessionScopeAttr", "세션 속성");
		application.setAttribute("applicationScopeAttr", "어플리케이션 속성");
// 		pageContext.include("/05/viewAttribute.jsp");
// 		response.sendRedirect(request.getContextPath() + "/05/viewAttribute.jsp");
	%>
	<a href="<%=request.getContextPath() %>/05/viewAttribute.jsp">확인하러 가즈아!</a>
</pre>
</body>
</html>












