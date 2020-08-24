<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/applicationDesc.jsp</title>
</head>
<body>
<h4> ServletContext application </h4>
<pre>
	Context : ex) Context Aware Computing
	ServletContext : 하나의 서블릿을 기준으로 현재 서블릿의 제어권을 
					 소유한 어플리케이션과 컨테이너에 대한 정보를 캡슐화, 싱글턴
	<%=application.hashCode() %>, <%=getServletContext().hashCode() %>
	1. MIME 확보 , <%=application.getMimeType("test.jpg") %>
	2. context 와 server 에 대한 정보 확보
	   <%=application.getContextPath() %>
	   <%=application.getServerInfo() %>
	   <%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
	3. logging
		<% application.log(" 임의 기록 로그 "); %>   
	4(*****). 웹 리소스 확보, 
		fileSystemPath getRealPath(url)
		InputStream getResouceAsStream(url)
		resource : 자원의 검색 방법
			1) file system resource : d:/contents/a.jpg, 파일 시스템의 루트부터 시작되는 절대 경로를 통해 접근.
			2) class path resource : kr.or.ddit.servlet01.ContentServlet
						클래스 패스 이후의 qualified name 의 형태로 접근.
			3) web resource : http://localhost/webStudy01/images/a.jpg
						웹 상에서 URL의 형태로 접근.
		<%
			String url = "/images/prod-1.jpg";
			String realPath = application.getRealPath(url);
			File imgFile = new File(realPath);
			out.println(realPath);
			out.println(imgFile.getAbsolutePath());
			File saveFolder = new File(application.getRealPath("/05"));
			File saveFile = new File(saveFolder, "prod-1.jpg");
			try(
// 				FileInputStream fis = new FileInputStream(imgFile);
				InputStream fis =  application.getResourceAsStream(url);		
				FileOutputStream fos = new FileOutputStream(saveFile);	
			){
				byte[] buffer = new byte[1024];
				int cnt = -1;
				while((cnt = fis.read(buffer))!=-1){
					fos.write(buffer, 0, cnt);
				}
			}
		%>									 
</pre>
<img src="<%=request.getContextPath() %>/05/prod-1.jpg" />
</body>
</html>







