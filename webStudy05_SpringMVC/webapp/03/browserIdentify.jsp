<%@page import="kr.or.ddit.enums.BrowserType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>03/browserIdentify.jsp/0511</title>
	</head>
	<body>
	<%
		String Agent = request.getHeader("User-Agent");
		out.println(String.format("<h3 style='color:red'>%s</h3>",Agent));
// 		if(Agent.contains("WOW"))
// 		{out.println("<script>alert('explore')</script>");}
// 		if(Agent.contains("Firefox")){out.println("<script>alert('firefox')</script>");}
// 		else{out.println("<script>alert('크롬이양')</script>");}
		String desc = BrowserType.browserIdentify(Agent);
		String msg = String.format("당신의 브라우저는 %s 입니다.",desc);
	%>
		<pre>
			사용자의 브라우저 종류를 식별 >> "당신의 브라우저는 xxx입니다." 라는 메시지를 alert창으로 출력 
			explorer : 익스플로어 , chrome : 크롬 , firefox : 파이어폭스, other : 기타 등등 
			----
			enums은  상수의 집합이므로 어떤값을 넘길것인지유의 
			식별할만한 키워드가 무엇인지 유의 
			---
			상수를 어떻게 잡고 설계할지가 관건(?)
		</pre>
	<script type="text/javascript">
		alert("<%=msg %>");
	</script>
	</body>
</html>