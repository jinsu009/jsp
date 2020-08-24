<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


 <style type="text/css">
 .error{color:blue;}
 </style>

</head>
<body>

	<form:form modelAttribute="newMember" method="post" action="${pageContext.request.contextPath }/login/insertMember.do" id="insertForm">
		<table>
			<tr>
				<th>아이디 (NN)</th>
				<td><input type="text" id="mem_id" name="mem_id" value="${newMember.mem_id }"/></td>
				<td><span id="idtext" style="color:red;"></span></td>
				<td>
					<input type="button" value="아이디 중복확인" id="IdConfirm">
					<form:errors path="mem_id" element="span" cssClass="error"/>
				</td>
				
			</tr>
			<tr>
				<th>비밀번호 (NN)</th>
				<td><input type="text" id="mem_pass" name="mem_pass" value="${newMember.mem_pass }"/></td>
				<td><form:errors path="mem_pass" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="text" id="re_mem_pass"/></td>
			</tr>
			<tr>
				<th>이름 (NN)</th>
				<td><input type="text" id="mem_name" name="mem_name" value="${newMember.mem_name }"/></td>
				<td><form:errors path="mem_name" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th>주민번호(6자리) (NN)</th>
				<td><input type="number" maxlength="6" id="mem_regno1" name="mem_regno1" value="${newMember.mem_regno1 }"/></td>
				<td><form:errors path="mem_regno1" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th>주민번호(7자리) (NN)</th>
				<td><input type="number" maxlength="7" id="mem_regno2" name="mem_regno2" value="${newMember.mem_regno2 }"/></td>
				<td><form:errors path="mem_regno2" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="date" id="mem_bir" name="mem_bir" value="${newMember.mem_bir }"/></td>
			</tr>
			<tr>
				<th>우편번호 (NN)</th>
				<td><input type="text" id="mem_zip" name="mem_zip" value="${newMember.mem_zip }"/></td>
				<td><form:errors path="mem_zip" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th>주소 (NN)</th>
				<td><input type="text" id="mem_add1" name="mem_add1" value="${newMember.mem_add1 }"/></td>
				<td><form:errors path="mem_add1" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th>상세주소 (NN)</th>
				<td><input type="text" id="mem_add2" name="mem_add2" value="${newMember.mem_add2 }"/></td>
				<td><form:errors path="mem_add2" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th>집 전화번호 (NN)</th>
				<td><input type="text" id="mem_hometel" name="mem_hometel" value="${newMember.mem_hometel }" /></td>
				<td><form:errors path="mem_hometel" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th>회사 전화번호 (NN)</th>
				<td><input type="text" id="mem_comtel" name="mem_comtel" value="${newMember.mem_comtel }" /></td>
				<td><form:errors path="mem_comtel" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td><input type="text" id="mem_hp" name="mem_hp" value="${newMember.mem_hp }" /></td>
			</tr>
			<tr>
				<th>이메일 (NN)</th>
				<td><input type="text" id="mem_mail" name="mem_mail" value="${newMember.mem_mail }"/></td>
				<td><form:errors path="mem_mail" element="span" cssClass="error"/></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" id="mem_job" name="mem_job" value="${newMember.mem_job }"/></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" id="mem_like" name="mem_like" value="${newMember.mem_like }"/></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="date" id="mem_memorialday" name="mem_memorialday"  value="${newMember.mem_memorialday }"/></td>
			</tr>
			<tr>
				<th>기념일 종류</th>
				<td><input type="text" id="mem_memorial" name="mem_memorial" value="${newMember.mem_memorial }"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="가입" id="insertBtn"/></td>
				<td><input type="reset" value="취소"/></td>
			</tr>
		</table>
		
		
	</form:form>
	<script type="text/javascript">
		// submit하기 전에 조건에 만족하지 않으면 화면이 전환되지 않도록 한다. 
		var insertForm =$("#insertForm").on("submit",function(){
			let validated = $(this).data("idValidated");
			
			if(!validated){
				// 중복체크를 안했거나 아이디가 중복인 상황
				alert("아이디 중복확인 하세요");
			}
			
			return validated;
		}).data("idValidated", false); // data를 숨겨놓는다. ?
				
		var memberid = insertForm.find("#mem_id").on("change",function(){
			insertForm.data("idValidated", false);
		});
				
		$("#IdConfirm").on("click",function(){
			
			let memid = memberid.val();
		 
			if(!memid) return; // 아이디가 입력되지않았다면 그냥 return
			
			$.ajax({
				url : "${pageContext.request.contextPath }/member/idValidate.do",
				data : { memid : memid },
				method : "post",
				dataType : "html",
				// Accept : 
				// html > text/html, Content-Type:text/html
				// json >  application/json 
				success : function(resp) {
					console.log(resp)
					let con = resp;
					$("#idtext").empty();
					if(con==1){
						insertForm.data("idValidated", false);
						$("#idtext").append("아이디 중복");
					}else{
						insertForm.data("idValidated", true);
						$("#idtext").append("사용가능한 아이디");
					}
				},
				error : function(errorResp) {
					console.log(errorResp.status + ":"+ errorResp.responseText);
				}
			});
			
		});
	</script>
