<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>01/gugudanForm.jsp</title>
		<script type="text/javascript">
			
		</script>
	</head>
	<body>
<!-- 		1. 클라이언트로부터 최소단과 최대단2개의 파라미터를 입력받는다. -->
<!-- 		2. submit이라는 이벤트 발생으로 gugudanservlet에게 파라미터가 전송되야한다. -->
<!-- 	<form action="gugudan.do"> -->
	<form>
		<ul>
			<li>최소단 : <input type="number" min="2" max="9" name="min"/></li>
			<li>최대단 : 
				<select name="max">
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
			if(StringUtils.isBlank(min)||StringUtils.isBlank(max)
				|| !StringUtils.isNumeric(min) || !StringUtils.isNumeric(max))
			{
				// 공백이나 숫자가 아닌 조건문 	
				
			}
			
			return valid;
		
		}
	%>
	
		<%
		// _jspservlet으로 코드화되어 전송된다. 
			String minStr = request.getParameter("min");
			String maxStr = request.getParameter("max");
			
			
			if(StringUtils.isBlank(minStr)){
				return;
			}
			
			if(!validate(minStr, maxStr)){
				// 검증실패 
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			
			int mindan = Integer.parseInt(minStr);
			int maxdan = Integer.parseInt(maxStr);
			
			for(int dan = mindan; dan<=maxdan; dan++){
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