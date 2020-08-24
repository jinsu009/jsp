<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login/loginForm.jsp/0512/0515</title>
		<%
			String msg = (String) session.getAttribute("message");
			if(StringUtils.isNoneBlank(msg)){		// 메세지가 있으면 
				%>
					<script type="text/javascript">
						alert("<%=msg %>");
					</script>
				<%
				session.removeAttribute("message");		// 스코프 : 기본객체 , 개념 , 
			}
		%>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/login/login.do" method="post">
			<ul>
			<%
				String failedId = request.getParameter("mem_id");
			//----------------------------쿠키 생성 
// 				Cookie[] idcookie = request.getCookies();
// 				String idtmp = null;
// 				for(Cookie tmp : idcookie){
// 					if(tmp.getName().equals("id")){
// 						idtmp = tmp.getValue();
// 					}
// 				}
			//-------------------------- 선생님
				CookieUtils cookieUtils = new CookieUtils(request);
				String saveId = cookieUtils.getCookieValue("idCookie");
			%>
				<li>
<%-- 					아이디 : <input type="text" name="mem_id" value="<%=idtmp==null? "" : idtmp %>"/> --%>
					아이디 : <input type="text" name="mem_id" value="<%=Objects.toString(saveId,"") %>"/>
					<input type="checkbox" name="idSave" value="saveId" <%=cookieUtils.exists("idCookie")?"checked":"" %> /> 아이디저장하기 
				</li>
				<li>
					비밀번호 : <input type="text" name="mem_pw"/>
				</li>					
				<input type="submit" value="로그인" />
			</ul>
		</form>
	</body>
</html>