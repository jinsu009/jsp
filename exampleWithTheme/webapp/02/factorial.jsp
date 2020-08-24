<%@page import="java.util.Objects"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/factorial.jsp</title>
</head>
<body>
<%
String numStr = request.getParameter("num");
%>

<!-- 사용자로부터 10이하의 숫자를 입력받아, factorial 연산 수행. -->
<!-- 1. jsp 하나로 모든 처리 -->
<!-- 2. recursive 호출 구조 사용 -->
<form action="<%=request.getContextPath() %>/02/factorial.jsp" method="get">
<input name="num"type="text" max="10" value="<%=Objects.toString(numStr, "") %>">
<input type="text" name="pName" />
<input type="submit" value="전송"> 
</form>
<%!
private boolean validate(String numStr){
	boolean chk =true;
	
	if(StringUtils.isBlank(numStr)){
		chk = false;
	}else{
		try{
			Integer.parseInt(numStr);
		}catch(NumberFormatException e){
			chk = false;
		}
	}
	
	return chk;
}
	private int factorial(int num, StringBuffer expression) {
// 		3! = 3*2*1;
		if(num<0) throw new IllegalArgumentException(num+"은 연산 불가");
		else if(num<=1){
			expression.append(num);
			return num;
		}
		else {
			expression.append(num+"*");
			return num*factorial(num-1, expression);
		}
	}
%>
<%
	
	
	if(StringUtils.isBlank(numStr)){
		return;
	}

	if (!validate(numStr)) {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}

	int numInt = Integer.parseInt(numStr);
	try{
		StringBuffer expression = new StringBuffer("(");
		int answer = factorial(numInt, expression);
		expression.append(")");
		out.println(String.format("%d!=%d %s", numInt, answer, expression));
	}catch(IllegalArgumentException e){
		response.sendError(400, e.getMessage());
		return;
	}
	
%>

</body>
</html>




