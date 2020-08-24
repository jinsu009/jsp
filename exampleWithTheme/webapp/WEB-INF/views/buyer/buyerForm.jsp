<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<form method="post" action="<c:url value='${currentAction }'/>">
<input type="hidden" readonly name="buyer_id" value="${buyer.buyer_id }" />
<table class="table">
	<tr class="row">
		<th class="col-3">거래처명</th>
		<td class="col-9"><input class="form-control" type="text" 
			name="buyer_name" value="${buyer.buyer_name }" />
			<span class="error">${errors.buyer_name }</span></td>
	</tr>
	<tr class="row">
		<th class="col-3">거래처분류코드</th>
		<td class="col-9">
			<select name="buyer_lgu" class="form-control" required>
				<option value="">상품분류</option>
				<c:forEach items="${lprodList }" var="lprodMap">
					<option value="${lprodMap['lprod_gu'] }"
						${buyer.buyer_lgu eq lprodMap['lprod_gu']?"selected":"" }
					>${lprodMap["lprod_nm"] }</option>
				</c:forEach>
			</select>
		
			<span class="error">${errors.buyer_lgu }</span></td>
	</tr>
	<tr class="row">
		<th class="col-3">은행명</th>
		<td class="col-9"><input class="form-control" type="text" name="buyer_bank"
			value="${buyer.buyer_bank }" />
			<span class="error">${errors.buyer_bank }</span></td>
	</tr>
	<tr class="row">
		<th class="col-3">계좌번호</th>
		<td class="col-9"><input class="form-control" type="text" name="buyer_bankno"
			value="${buyer.buyer_bankno }" />
			<span class="error">${errors.buyer_bankno }</span></td>
	</tr>
	<tr class="row">
		<th class="col-3">계좌주</th>
		<td class="col-9"><input class="form-control" type="text"
			name="buyer_bankname" value="${buyer.buyer_bankname }" />
			<span class="error">${errors.buyer_bankname }</span></td>
	</tr>
	<tr class="row">
		<th class="col-3">우편번호</th>
		<td class="col-9"><input class="form-control" type="text" name="buyer_zip"
			value="${buyer.buyer_zip }" />
			<span class="error">${errors.buyer_zip }</span></td>
	</tr>
	<tr class="row">
		<th class="col-3">주소1</th>
		<td class="col-9"><input class="form-control" type="text" name="buyer_add1"
			value="${buyer.buyer_add1 }" />
			<span class="error">${errors.buyer_add1 }</span></td>
	</tr>
	<tr class="row">
		<th class="col-3">주소2</th>
		<td class="col-9"><input class="form-control" type="text" name="buyer_add2"
			value="${buyer.buyer_add2 }" />
			<span class="error">${errors.buyer_add2 }</span></td>
	</tr>
	<tr class="row">
		<th class="col-3">회사전번</th>
		<td class="col-9"><input class="form-control" type="text" required
			name="buyer_comtel" value="${buyer.buyer_comtel }" />
			<span class="error">${errors.buyer_comtel }</span></td>
	</tr>
	<tr class="row">
		<th class="col-3">팩스번호</th>
		<td class="col-9"><input class="form-control" type="text" required
			name="buyer_fax" value="${buyer.buyer_fax }" />
			<span class="error">${errors.buyer_fax }</span></td>
	</tr>
	<tr class="row">
		<th class="col-3">이메일</th>
		<td class="col-9"><input class="form-control" type="text" required
			name="buyer_mail" value="${buyer.buyer_mail }" />
			<span class="error">${errors.buyer_mail }</span></td>
	</tr>
	<tr class="row">
		<th class="col-3">담당자명</th>
		<td class="col-9"><input class="form-control" type="text"
			name="buyer_charger" value="${buyer.buyer_charger }" /><span
			class="error">${errors.buyer_charger }</span></td>
	</tr>
	<tr class="row">
		<th class="col-3">내선번호</th>
		<td class="col-9"><input class="form-control" type="text" name="buyer_telext"
			value="${buyer.buyer_telext }" />
			<span class="error">${errors.buyer_telext }</span></td>
	</tr>
	<tr class="row">
		<td class="col">
			<input type="submit" value="저장" class="btn btn-success"/>
			<input type="reset" value="취소" class="btn btn-warning"/>
			<a class="btn btn-secondary byAjax" href='<c:url value="/buyer/buyerList.do"/>'>목록으로</a>
		</td>
	</tr>
</table>
</form>