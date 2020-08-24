<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- includee/topMenu.jsp/0515 -->
  <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
   <div class="container d-flex flex-column flex-md-row justify-content-between">
    <a class="py-2 d-none d-md-inline-block" href="${pageContext.request.contextPath }/member/memberList.do">회원관리</a>
    <a class="py-2 d-none d-md-inline-block" href="${pageContext.request.contextPath }/ddit/dditStudents.do">학생관리 </a>
    <a class="py-2 d-none d-md-inline-block" href="${pageContext.request.contextPath }/prod/prodList.do">상품관리</a>
    <a class="py-2 d-none d-md-inline-block" href="#">거래처관리</a>
    <a class="py-2 d-none d-md-inline-block" href="#">Support</a>
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
