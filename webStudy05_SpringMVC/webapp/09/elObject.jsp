<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/elObject.jsp/0528</title>
</head>
<body>
<h3 style="color:blue">EL 기본객체</h3>

<pre>
1~5 : 전부 map 

	1. Scope 객체 (Map&lt;String,Object&gt;):
		pageScope , requestScope, sessionScope, applicationScope
		${applicationScope.attr_name } : 범위가 좁은 영역부터 검색 
		
		연산배열 구조 : 
<%-- 			${applicatioScope["attr_name"] } --%>
		
	2. Parameter 객체 : param(Map&lt;String,String&gt;), paramValues(Map&lt;String,String[]&gt;)
	-- queryString 사용해서 값 보내주기 
<%-- 		표현식 : <%=request.getParameter("name") %>  --%>
		EL : ${param.name }
<%-- 		연산배열 구조 : ${param["name"] } --%>
		---
<%-- 		표현식 : <%=request.getParameterValues("name")[0] %> --%>
		EL : ${paramValues.name[1] }
<%-- 		연산배열 구조 ${paramValues.["name"][0] } --%>
		
	3. request Header 객체
		header(Map&lt;String,String&gt;), headerValues(Map&lt;String,String[]&gt;)
		<%=request.getHeader("Accept") %>
		${header.accept }
		${header["accept"] }
		<%=request.getHeader("User-Agent") %>
		${header.user-agent }
		${header["user-agent"] }
		${headerValues["user-agent"] }
		
	4. Context Parameter 객체 
		initParam(Map&lt;String,String&gt;)
		<%=application.getInitParameter("contentPath") %>
		${initParam.contentPath }
		
	5. Cookie 객체  
		cookie(Map&lt;String,Cookie&gt;)
		<%=new CookieUtils(request).getCookieValue("JSESSIONID") %>
		${cookie.JSESSIONID.value }
		${cookie.JSESSIONID.name }
		
	6. PageContext : 유일하게 코드어시스트가능
	${pageContext.request.contextPath }
</pre>
</body>
</html>




