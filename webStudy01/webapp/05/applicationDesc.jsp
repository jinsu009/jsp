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
		<title>05/applicationDesc.jsp/0514</title>
	</head>
	<body>
		<h3 style="color:orange;"> ServletContext application </h3>
		<pre>
			Context : 나(Servlet)를 둘러싼 다양한 환경을 가지고 있는 객체  
				ex) Context Aware Computing : 상황 인지(인식) 컴퓨팅
			
			tomcat > webStudy01 
			
			ServletContext : 하나의 서블릿을 기준으로 현재 서블릿의 제어권을 소유한 
							   어플리케이션과 컨테이너에 대한 정보를 캡슐화 
				* 특징 
					1) 싱글턴
					application.hashCode() :
						<span style="color:red"><%=application.hashCode()%></span>
					getServletContext().hashCode() :
						<span style="color:red"><%=getServletContext().hashCode() %></span>
		
			* 언제 사용하는지 
			1. MIME 확보 : <span style="color:red"><%=application.getMimeType("test.jpg") %></span>
			
			2. context와 server의 정보를 확보 :
			<span style="color:red"><%=application.getContextPath() %></span>
			<span style="color:red"><%=application.getServerInfo() %></span>
			
			현재 실행환경의 servlet ver. : 3.1
			자신의 언어가 사용될수 있는 환경인지 알기 위해 servlet의 변경을 알기 위한 getter
			<%=application.getMajorVersion() %> 
			<%=application.getMinorVersion() %>
			
			3. logging : 데이터 기록 후 분석하는 행위
				 > 무작위로 기록을 하는 것이 아니라 읽기 쉽게 기록해야한다. : 형식 필요
				 > log.xml 에서 확인할 수 있다.
				 > 어디(append), 누가(logger), 어떤 형식(layout)으로 어떤 이벤트 종류(level) 일 때   
				<% application.log("임의 기록 로그"); %>
			* log : performace 향상, 기록  , 디버깅, 튜닝 > server를 위한 기록
			
			4.(★★★★★★) 웹 리소스 확보 :
				- fileSystemPath getrealpath(url)
				- InputStream getResourceAsStream(url)
				<%
				// 서버 측에서 경로 설정
				String url = "/images/cat.jpg";
			
				// 클라이언트 측에서 경로 설정
// 				String url =request.getContextPath() +  "/images/cat.jpg"; // webStudy01 가 두번 사용된다 
				// getRealPath() : virtual path 를 넘기면 realpath가 넘어온다
				String realPath = application.getRealPath(url);
					File imgFile = new File(realPath);
				out.println("realPath : " + realPath);
				out.println("imgFile.getAbsolutePath() : "+imgFile.getAbsolutePath()); // 파일시스템상의 절대 경로
				
				File saveFolder = new File(application.getRealPath("/05"));
				File saveFile = new File(saveFolder, "cat.jpg");
				
				try(
// 					FileInputStream fis = new FileInputStream(imgFile);
					InputStream fis = application.getResourceAsStream(url);
					FileOutputStream fos = new FileOutputStream(saveFile);
				){
					byte[] buffer = new byte[1024];
					int cnt = -1;
					while((cnt = fis.read(buffer))!=-1){
						fos.write(buffer,0,cnt);
					}
				}
				
				%>				 
				- server안에 폴더에 복사가 됐다 . 
				- resource : 접근방법에 따라 세가지 분류
				- Web Resource : 웹 상에서 URL의 형태로 접근 (http://localhost/webStudy01/images/cat.jpg)
				- File System Resource : 파일 시스템의 루트부터 시작되는 절대 경로를 통해 접근 (d:/contents/cat.jpg)
				- Class path Resource : 클래스 패스 이후의 qualified name의 형태로 접근 (kr.or.ddit.servlet01/ContentServlet)
		</pre>
		<hr>
		<img src="<%=request.getContextPath() %>/05/cat.jpg"/>
	</body>
</html>