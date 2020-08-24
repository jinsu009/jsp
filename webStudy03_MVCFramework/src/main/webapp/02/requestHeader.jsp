<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>02/requestHeader.jsp/20200508</title>
	</head>
	<body>
		<h4>요청 부가 정보(request header : meta data)</h4>
		<pre>
			body encoding name : <%=request.getCharacterEncoding() %>
			
			content's length : <%=request.getContentLength() %>
			간혹 사이트에 파일을 업로드 할 때마다 파일의 길이에 제한을주는데 
			업로드 되기 전 파일의 크기를 읽어서 검수할때 사용
			
			content's mime : <%=request.getContentType() %>
			
			context path : <%=request.getContextPath() %>
			
			local address (on server) : <%=request.getLocalAddr() %> 서버의 주소 
			remote address (on client) : <%=request.getRemoteAddr() %> 클라이언트의 주소
			
			http method (Request Line) : <%=request.getMethod() %> 
			protocol : <%=request.getProtocol() %>
			======================================
			URI : <%=request.getRequestURI() %>
			URL : <%=request.getRequestURL() %>
			-----
			자원을 식별하기 위해 여러가지 방식을 사용했지만 돌고돌아 url만 사용하게 됐다
			
			--- header의 이름을 몰라도 정보를 빼내올 수 있다
			accept header : <%=request.getHeader("Accept") %>
			
			
			--- 20200511 추가 
			client Locale : <%=request.getLocale()%>
			
		</pre>
		<table>
			<thead>
				<tr>
					<th>header name</th>
					<th>header value</th>
				</tr>
			</thead>
			<tbody>
			<% 
				Enumeration<String> headerNames = request.getHeaderNames();
				while(headerNames.hasMoreElements()){
					String name = headerNames.nextElement();
					String value = request.getHeader(name);
					out.println(String.format("<tr><td>%s</td><td>%s</td></tr>", name, value));
				}
			%>
			</tbody>
		</table>
	</body>
</html>