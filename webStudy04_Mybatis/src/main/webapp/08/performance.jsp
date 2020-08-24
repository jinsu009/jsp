<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>08/performance.jsp/0521</title>
	</head>
	<body>
		<h3 style="color:aqua; background-color: violet">성능체크와 성능향상 요인</h3>
		<pre>
			- 성능 테스트 : jmeter
				1. 시간 : response time = latency time + processing time
					- 한번의 지연시간과 한번의 처리 시간 : 30ms, 17ms , (pooling service이후) 1ms
					- 100번의 지연시간과 100번의 처리시간 : 800ms , (pooling service이후) 35ms
					- 한번의 지연시간과 100번의 처리시간 : 32ms
					 
					- Pooling 시스템을 통해 latency time 을 감소 
					 
				2. 공간 : memory 
		
		
		</pre>
	</body>
</html>