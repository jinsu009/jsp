<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/requestHeader.jsp</title>
</head>
<body>
<h4> 요청 부가 정보 (request header : meta data)</h4>
<pre>
body encoding name : <%= request.getCharacterEncoding() %>
content's length : <%=request.getContentLength() %>
content's mime : <%=request.getContentType() %>
context path : <%=request.getContextPath() %>
local address (on server) : <%=request.getLocalAddr() %>
remote address (on client): <%=request.getRemoteAddr() %>
http method (Request Line) : <%=request.getMethod() %>
protocol : <%=request.getProtocol() %>
URI : <%=request.getRequestURI() %>
URL : <%=request.getRequestURL() %>
accept header : <%=request.getHeader("Accept") %>
client Locale : <%=request.getLocale() %>
</pre>
<table>
	<thead>
		<tr>
			<th>헤더명</th>
			<th>헤더값</th>
		</tr>
	</thead>
	<tbody>
<%
	Enumeration<String> headerNames = request.getHeaderNames();
	while(headerNames.hasMoreElements()){
		String name = headerNames.nextElement();
		String value = request.getHeader(name);
		out.println(
			String.format("<tr><td>%s</td><td>%s</td></tr>", name, value)		
		);
	}
%>
	</tbody>
</table>
</body>
</html>











