<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/mypage.jsp/0527/수정</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/jquery-ui-1.12.1/jquery-ui.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/js/bootstrap.bundle.min.js"></script>
	<c:if test="${ not empty msg }">
		alert("${msg}");
		<c:remove var="msg" scope="session"/>
	</c:if>
<%
	// session.removeAttribute("msg");
	// flash attribute : 한번만 사용하고 지워지는 attribute
%>
</head>
<body>
	<%
		MemberVO memVO = (MemberVO) request.getAttribute("memVO");
	%>
	<form id="updateForm" method="post" action="${pageContext.request.contextPath }/member/updateMember.do">
		<table>
			<tr>
				<th>아이디</th>
				<td>${memVO.mem_id }</td>
				<td><input id="mem_id" name="mem_id" type="hidden"
					value="${memVO.mem_id }" /></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input id="mem_name" name="mem_name" type="text"
					value="${memVO.mem_name }" /></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>${memVO.mem_pass }</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>${memVO.mem_bir }</td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input id="mem_add1" name="mem_add1" type="text"
					value="${memVO.mem_add1 }" /></td>
				<td><input id="mem_add2" name="mem_add2" type="text"
					value="${memVO.mem_add2 }" /></td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td><input id="mem_hp" name="mem_hp" type="text"
					value="${memVO.mem_hp }" /></td>
			</tr>
			<tr>
				<th>메일</th>
				<td><input id="mem_mail" name="mem_mail" type="text"
					value="${memVO.mem_mail }" /></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input id="mem_job" name="mem_job" type="text"
					value="${memVO.mem_job }" /></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input id="mem_like" name="mem_like" type="text"
					value="${memVO.mem_like }" /></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input id="mem_memorialday" name="mem_memorialday"
					type="text" value="${memVO.mem_memorialday }" /></td>
			</tr>
			<tr>
				<th>기념일종류</th>
				<td><input id="mem_memorial" name="mem_memorial" type="text"
					value="${memVO.mem_memorial }" /></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>${memVO.mem_mileage }</td>
				<td><input id="mem_mileage" name="mem_mileage" type="hidden"
					value="${memVO.mem_mileage }" /></td>
			</tr>
		</table>
		<input type="reset" value="취소" /> <input type="submit" value="수정" />
<%-- 		<a href="<%=request.getContextPath()%>/member/deleteMember.do"><input id="deleteBtn" type="button" value="탈퇴" /></a> --%>
		<input id="deleteBtn" type="button" value="탈퇴" />
		<!-- form에는 영향을 주지 않는다. -->
		<input type="button" value="뒤로가기 "/>
	</form>
	<form id="delForm" action="${pageContext.request.contextPath }/member/deleteMember.do" method="post">
		<input type="hidden" name="mem_id" value="${memVO.mem_id }" />
		<input type="hidden" name="mem_pass"/>
	</form>
	<script type="text/javascript">
		var delForm = $("#delForm");
		var passInput = $("updateForm input[name='mem_pass']");
		$("#deleteBtn").on("click", function() {
			//let password = passInput.val();
			let password = prompt("비밀번호 입력");
			delForm.find("[name='mem_pass']").val(password);
			delForm.submit();
		});
	</script>
</body>
</html>