<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!-- includee/topMenu.jsp/0515 -->
  <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
   <div class="container d-flex flex-column flex-md-row justify-content-between">
   <span class="py-2 d-none d-md-inline-block">
   	<input width="40px" height="40px" type="image" src="${cPath}/images/us.jpg" onclick="location.href='${cPath}?lang=en';"/>
   	<input width="40px" height="40px"  type="image" src="${cPath}/images/korea.jpg" onclick="location.href='${cPath}?lang=ko';"/>
   </span>
    <a class="py-2 d-none d-md-inline-block" href="${pageContext.request.contextPath }/ddit/dditStudents.do">
    	<spring:message code="menu1" />
    </a>
    <a class="py-2 d-none d-md-inline-block" href="${pageContext.request.contextPath }/member/memberList.do">
    	<spring:message code="menu2" />
    </a>
    <a class="py-2 d-none d-md-inline-block" href="${pageContext.request.contextPath }/prod">
   	 	<spring:message code="menu3" />
    </a>
    <a class="py-2 d-none d-md-inline-block" href="#">
   	 	<spring:message code="menu4" />
    </a>
    <a class="py-2 d-none d-md-inline-block" href="#">
    	<spring:message code="menu5" />
    </a>
    <a class="py-2 d-none d-md-inline-block" href="#">
    	<spring:message code="menu6" />
    </a>
   <%
   		MemberVO authUser = (MemberVO) session.getAttribute("authUser");
   		if(authUser==null){
		%>
		    <a class="py-2 d-none d-md-inline-block" href="${pageContext.request.contextPath }/login/loginForm.jsp">로그인</a>
		    <a class="py-2 d-none d-md-inline-block" href="${pageContext.request.contextPath }/member/insertMember.do">회원가입</a>
		<%
   		}else{
		%>
		    <a class="py-2 d-none d-md-inline-block" href="${pageContext.request.contextPath }/mypage.do"> <%=authUser.getMem_name() %>님(${authUser.mem_roles })</a>
		    <a id="logoutBtn" class="py-2 d-none d-md-inline-block" href="#"> 로그아웃</a>
   		<%
   		}
   %>
   <form id="logoutForm" action="${pageContext.request.contextPath }/login/logout.do" method="post">   </form>
  </div>
</nav>
<script type="text/javascript">
	var logoutForm = $("#logoutForm");
	$("#logoutBtn").on("click",function(){
		logoutForm.submit();
	});
</script>
