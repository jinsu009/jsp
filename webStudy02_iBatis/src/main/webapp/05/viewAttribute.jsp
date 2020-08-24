<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>05/viewAttribute.jsp/0514</title>
	</head>
	<body>
		<pre style="background-color: lime;">
			page scope : 
				<%=pageContext.getAttribute("pageScopeAttr") %>
			request scope :
					<%=request.getAttribute("requestScopeAttr") %>
			session scope : 
					<%=session.getAttribute("sessionScopeAttr") %>
			<%
				// flash attribute : 저장 후 한번 꺼내고 , 삭제
				session.removeAttribute("sessionScopeAttr");
			%>
			application scope :
				<%=application.getAttribute("applicationScopeAttr") %>
		</pre>
	</body>
</html>