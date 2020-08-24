<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<form method="post" id="insertForm" action="<c:url value='/member/insertMember.do'/>">
	<table class="table text-center">
		<tr class="row">
			<th class="col-3">회원아이디</th>
			<td class="col-9 form-inline">
				<input class="col-5 form-control mr-2" type="text" id="mem_id" name="mem_id" required
						value="${member.mem_id }">
				<input type="button" id="idCheck" class="btn btn-success mr-2" value="아이디중복확인"/>
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
			<td class="col text-left">
				<input class="btn btn-warning mr-2" type="reset" value="취소"> 
				<input class="btn btn-success mr-2" type="submit" value="저장">
				<input class="btn btn-secondary mr-2" type="submit" value="뒤로가기"
					onclick="history.back();"
				>
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	var insertForm = $("#insertForm").on("submit", function(){
		let validated = $(this).data("idValidated");
		if(!validated){
			alert("아이디 중복 확인하세요.");
		}
		return validated;
	}).data("idValidated", false);
	var idInput = insertForm.find("#mem_id").on("change", function(){
		insertForm.data("idValidated", false);
		idInput.next("#msgSpan").remove();
	});
	$("#idCheck").on("click", function(){
		let inputId = idInput.val();
		if(!inputId) return;
		idInput.next("#msgSpan").remove();
		$.ajax({
			url : "${pageContext.request.contextPath }/member/idValidate.do",
			data : {
				inputId : inputId
			},
			method : "post",
			dataType : "json", // Accept:application/json, Content-Type:application/json
			success : function(resp) {
				let message = null;
				if(resp.result=="OK"){
					insertForm.data("idValidated", true);
					message = "아이디 사용 가능";
				}else{
					insertForm.data("idValidated", false);
					message = "아이디 중복";
				}
				idInput.after( $("<span>").prop('id', 'msgSpan').text(message) );
			},
			error : function(errorResp) {
				console.log(errorResp.status + ":" + errorResp.responseText);
			}
		});
	});
</script>
