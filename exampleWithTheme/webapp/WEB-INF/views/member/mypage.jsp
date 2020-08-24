<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty message}">
	<script type="text/javascript">
			alert("${message }");
	</script>	
	<c:remove var="message" scope="session"/>
</c:if>
<form id="updateForm" action="${pageContext.request.contextPath }/member/updateMember.do" method="post">
	<table class="table text-center">
		<tr class="row">
			<th class="col-3">회원아이디</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_id" required readonly
				value="${member.mem_id }">
				<span class="error">${errors["mem_id"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">비밀번호(${member.mem_pass })</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_pass" required >
				<span class="error">${errors["mem_pass"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">회원명</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_name" required
				value="${member.mem_name }">
				<span class="error">${errors["mem_name"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">주민번호1</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_regno1" required
				value="${member.mem_regno1 }">
				<span class="error">${errors["mem_regno1"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">주민번호2</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_regno2" required
				value="${member.mem_regno2 }">
				<span class="error">${errors["mem_regno2"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">생일</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="date" name="mem_bir"
				value="${member.mem_bir }">
				<span class="error">${errors["mem_bir"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">우편번호</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_zip" required
				value="${member.mem_zip }">
				<span class="error">${errors["mem_zip"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">주소1</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_add1" required
				value="${member.mem_add1 }">
				<span class="error">${errors["mem_add1"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">주소2</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_add2" required
				value="${member.mem_add2 }">
				<span class="error">${errors["mem_add2"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">집전화번호</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_hometel" required
				value="${member.mem_hometel }">
				<span class="error">${errors["mem_hometel"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">회사전화번호</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_comtel" required
				value="${member.mem_comtel }">
				<span class="error">${errors["mem_comtel"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">휴대폰</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_hp"
				value="${member.mem_hp }">
				<span class="error">${errors["mem_hp"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">이메일</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_mail" required
				value="${member.mem_mail }">
				<span class="error">${errors["mem_mail"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">직업</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_job"
				value="${member.mem_job }">
				<span class="error">${errors["mem_job"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">취미</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_like"
				value="${member.mem_like }">
				<span class="error">${errors["mem_like"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">기념일</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="text" name="mem_memorial"
				value="${member.mem_memorial }">
				<span class="error">${errors["mem_memorial"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">기념일자</th>
			<td class="col-9 form-inline">
			<input class="col-5 form-control mr-2" type="date" name="mem_memorialday"
				value="${member.mem_memorialday }">
				<span class="error">${errors["mem_memorialday"] }</span></td>
		</tr>
		<tr class="row">
			<th class="col-3">마일리지</th>
			<td class="col-9 form-inline">${member.mem_mileage }</td>
		</tr>
		<tr class="row">
			<th class="col-3">탈퇴여부</th>
			<td class="col-9 form-inline">${member.mem_delete }</td>
		</tr>
		<tr class="row">
			<td class="col text-left">
				<input class="btn btn-warning mr-2" type="reset" value="취소"> 
				<input class="btn btn-success mr-2" type="submit" value="수정">
				<input type="button" class="btn btn-danger mr-2" value="탈퇴" id="delBtn">
				<input type="button" class="btn btn-secondary mr-2" value="뒤로가기" 
					onclick="history.back()" >
			</td>
		</tr>
	</table>
</form>
<form id="delForm" action="${pageContext.request.contextPath }/member/deleteMember.do" method="post">
	<input type="hidden" name="mem_id" value="${member.mem_id }">
	<input type="hidden" name="mem_pass" >
</form>
<script type="text/javascript">
	var delForm = $("#delForm");
	var passInput = $("#updateForm input[name='mem_pass']");
	$("#delBtn").on("click", function(){
		let password = passInput.val();
		delForm.find("[name='mem_pass']").val(password);
		delForm.submit();
	});
</script>








