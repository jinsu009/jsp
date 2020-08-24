<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>buyerForm 가입</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/jquery-ui-1.12.1/jquery-ui.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/main.css">
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
</head>
<body>
	<h3 style="color: green; text-align: center;">Buyer 등록/수정 하기</h3>
	<hr>

	<form method="post">
	<input type="hidden" name="buyer_id" value="${buyer.buyer_id }">
		<table style="text-align: center"; >
			<tr>
				<th>buyer_name(NN)</th>
				<td><input type="text" name="buyer_name" id="buyer_name"
					value="${buyer.buyer_name }" /></td>
			</tr>
			<tr>
				<th>buyer_lgu(NN)</th>
				<td>
				<select>
					<c:forEach items="${prodList}" var="map">
						<option>제품군</option>
<%-- 						<option ${not empty buyer and  map.TEXT eq buyer.prod_lgu?"selected":"" } value="${map.CODE}">${map.TEXT}</option> --%>
						<option value="${map.buyer_lgu}">${map.buyer_lgu}</option>
					</c:forEach>
					
				</select>
				<input type="text" name="buyer_lgu" id="buyer_lgu"
					value="${buyer.buyer_lgu }"></td>
			</tr>
			<tr>
				<th>buyer_bank</th>
				<td><input type="text" name="buyer_bank" id="buyer_bank"
					value="${buyer.buyer_bank }"></td>
			</tr>
			<tr>
				<th>buyer_bankno</th>
				<td><input type="text" name="buyer_bankno" id="buyer_bankno"
					value="${buyer.buyer_bankno}"></td>
			</tr>
			<tr>
				<th>buyer_bankname</th>
				<td><input type="text" name="buyer_bankname" id="buyer_bankname"
					value="${buyer.buyer_bankname }"></td>
			</tr>
			<tr>
				<th>buyer_zip</th>
				<td><input type="text" name="buyer_zip" id="buyer_zip"
					value="${buyer.buyer_zip }"></td>
			</tr>
			<tr>
				<th>buyer_add1</th>
				<td><input type="text" name="buyer_add1" id="buyer_add1"
					value="${buyer.buyer_add1 }"></td>
			</tr>
			<tr>
				<th>buyer_add2</th>
				<td><input type="text" name="buyer_add2" id="buyer_add2"
					value="${buyer.buyer_add2 }"></td>
			</tr>
			<tr>
				<th>buyer_comtel(NN)</th>
				<td><input type="text" name="buyer_comtel" id="buyer_comtel"
					value="${buyer.buyer_comtel }"></td>
			</tr>
			<tr>
				<th>buyer_fax(NN)</th>
				<td><input type="text" name="buyer_fax" id="buyer_fax"
					value="${buyer.buyer_fax }"></td>
			</tr>
			<tr>
				<th>buyer_mail(NN)</th>
				<td><input type="text" name="buyer_mail" id="buyer_mail"
					value="${buyer.buyer_mail }"></td>
			</tr>
			<tr>
				<th>buyer_charger</th>
				<td><input type="text" name="buyer_charger" id="buyer_charger"
					value="${buyer.buyer_charger }"></td>
			</tr>
		</table>
		<div style="text-align: center";>
			<input type="submit" value="완료"/> 
			<input type="reset" value="입력 전체 취소" />
			 <input type="button" value="뒤로가기 " />
		</div>
	</form>
</body>
</html>