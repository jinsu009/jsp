<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>02/factorial.jsp/20200508</title>
	</head>
	<body>
	<%
		String number = request.getParameter("num");
	%>
		<pre>
			클라이언트로부터 10이하의 숫자를 입력받아서 factorial 연산 수행
			
			- 조건
				1. jsp 하나로 모두 처리 : 입력받은 데이터를 보낼때 현재페이지로 전송 
				2. 재귀호출 (recursive 호출 구조 사용) : 선언부가 필요할 것이 다.
				* 재귀호출 : 장 > 코드의 간결함, 단 > 무한재귀호출의 위험성
				
			- input type = number : 로 했기때문에 value가 없어도 null이 출력되지 않는다. 
			  input type = text 라면 value값이 없을 때 입력값이 없으면 null이 출력된다. value=number
			  input type = text 라면 value = Objects.toString(number,"")
			  
			- form : 데이터를 보내는 유일한 태그
			 
		</pre>
		<hr>
		<!-- 주소가 아무것도 없으면 현재 자기 브라우저의 주소를 참조하고 있는 것  -->
		<form method="get" action="<%=request.getContextPath() %>/02/factorial.jsp">
			<input type="text" max="10" name="num" placeholder="1~10입력" value="<%=Objects.toString(number,"")%>"/>
			<input type="text" name="pName"/>
			
			<button type="submit">전송</button><br>
		</form>
		<hr>
		<div id="result">
		<%!
			private boolean validate(String num){
				boolean valid = true;
				if(StringUtils.isBlank(num)){
					valid = false;
				}else{
					try{
						Integer.parseInt(num);
					}catch(NumberFormatException e){
						// 숫자가 아닌게 넘어온다.
					 	valid = false;
					}
				}
				return valid;
			}
		
			private int factorial(int num, StringBuffer expression){
				
				if(num<0) throw new IllegalArgumentException(num + " 은 연산 불가 ");
				//unchecked exception : 예외를 처리 하지 않아도 되는 형태
				else if(num<=1){ 
					expression.append(num);
					return num;
				}
				else {
					expression.append(num + "*");
					return num*factorial(num-1, expression);
				}
			}
		%>
		<%
				if(StringUtils.isBlank(number)){
					return;
				}
				if(!validate(number)){
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}
		
				int num =Integer.parseInt(number);	
				try { 
					// StringBuffer 은 객체이다. 
					StringBuffer expression = new StringBuffer("(");
					int result = factorial(num,expression); 
					expression.append(")");
					
					out.print(String.format("%d! = %d  %s",num , result, expression));
				}
				catch(IllegalArgumentException e){ 
					response.sendError(400,e.getMessage());
				}
			%>
		</div>
	</body>
</html>