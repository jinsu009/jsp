<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 1. 클라이언트로부터 최소단과 최대단 2개의 파라미터 입력 받음. -->
<!-- 2. submit 이벤트 발생으로 GugudanServlet 에게 파라미터 전송.  -->
<form>
	<ul>
		<li>최소단: <input type="number" min="2" max="9" name="minDan" /></li>
		<li>최대단: 
			<select name="maxDan">
				<%
					for(int i=2; i<=9; i++){
						%>
						<option><%=i %></option>
						<%					
					}
				%>
			</select>
			<button type="submit">전송</button>
		</li>	
	</ul>
</form>
<table>
<%!
private boolean validate(String min, String max){
	boolean valid = true;
	if(StringUtils.isBlank(min) || StringUtils.isBlank(max)
			|| !StringUtils.isNumeric(min) || !StringUtils.isNumeric(max)
			){
		valid = false;
	}
	return valid;
}
%>
<%
	String minStr = request.getParameter("minDan");
	String maxStr = request.getParameter("maxDan");
	if(StringUtils.isBlank(minStr)){
		return;
	}
	if(!validate(minStr, maxStr)){
		// 검증 실패
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}
	int minDan = Integer.parseInt(minStr);
	int maxDan = Integer.parseInt(maxStr);
	for(int dan = minDan; dan<=maxDan; dan++){
		%>
		<tr>
		<%
		for(int mul=1; mul<=9; mul++){
			%>
			<td><%=String.format("%d*%d=%d", dan, mul, (dan*mul)) %></td>
			<%
		}
		%>
		</tr>
		<%
	}
%>
</table>
</body>
</html>







