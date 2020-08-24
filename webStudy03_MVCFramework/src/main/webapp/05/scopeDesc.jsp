<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>05/scopeDesc.jsp/0514</title>
	</head>
	<body>
		<h4 style="color:gray;">Scope (영역)</h4>
		<pre>
			§ Scope
			 : 웹 어플리케이션에서 데이터를 공유할 목적으로 관리하는 저장 공간
			 : 생존 범위(사용범위) 가 제한된 4개의 기본 객체가 관리하는 공간 (Map)
			 : scope를 통해 공유되는 데이터 : Attribute(name, value) 
				1. page[Context] - : page context 가 가지고 있는 저장 공간
					> pageContext에 의해 제어가 된다. 
					> 범위가 하나의 jsp로 제한이 되어 있다.
					 
				2. request - : request 가 가지고 있는 저장공간
					> request에 의해 제어된다. 
					> 해당 request가 소멸되는 순간 공간의 사용종료
					
				3. session - : session이 가지고 있는 저장공간
					> session에 의해 제어된다. 
					> 해당 session이내에서만 사용
					> 독립적인 session scope를 가지고 있기 때문에 공유 할 수 없다. 
					
				4. application - : servlet context 가 가지고 있는 저장 공간
					> ServletContext 에 의해 제어된다. 
					예) 카페에 접속했을 때 나오는 접속자 명단 이 나오는데 모두가 공통적으로 접근할수 있는 공간인 application에 작성해야한다. 
			
			
			저장 > setAttribute(String name, Object value)
			공유 > getAttribute(name)
			삭제 > removeAttribute(name)
			
			<%
				pageContext.setAttribute("pageScopeAttr", "페이지 속성");
				request.setAttribute("requestScopeAttr", "요청 속성");
				session.setAttribute("sessionScopeAttr", "세션 속성");
				application.setAttribute("applicationScopeAttr", "어플리케이션 속성");
				
				// 각자 다른 page에서 처리가 되므로 page는 공유될수 없다. 
// 				pageContext.forward("/05/viewAttribute.jsp");
// 				pageContext.include("/05/viewAttribute.jsp");
				
//				쿠키가 없으면 session = null 이 나오게 된다. 
// 				response.sendRedirect(request.getContextPath()+"/05/viewAttribute.jsp");
			%>
			<a href="<%=request.getContextPath() %>/05/viewAttribute.jsp">확인하러 가쟈 </a>
			
			session과 application은 데이터가 계속 쌓이게 된다. 
			그래서 영역을 지정해놓고 사용하도록 해야한다. (최소한의 영역)
			application > 잘못된 사용으로 과부화가 오게된다. 
		</pre>
	</body>
</html>