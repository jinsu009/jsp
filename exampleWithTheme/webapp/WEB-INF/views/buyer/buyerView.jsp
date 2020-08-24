<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
	<table class="table table-bordered">
		<tr>
			<th>거래처아이디</th>
			<td>${buyer.buyer_id }</td>
		</tr>
		<tr>
			<th>거래처명</th>
			<td>${buyer.buyer_name }</td>
		</tr>
		<tr>
			<th>거래처분류코드</th>
			<td>${buyer.buyer_lgu }</td>
		</tr>
		<tr>
			<th>은행명</th>
			<td>${buyer.buyer_bank }</td>
		</tr>
		<tr>
			<th>계좌번호</th>
			<td>${buyer.buyer_bankno }</td>
		</tr>
		<tr>
			<th>계좌주</th>
			<td>${buyer.buyer_bankname }</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${buyer.buyer_zip }</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${buyer.buyer_add1 }</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${buyer.buyer_add2 }</td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td>${buyer.buyer_comtel }</td>
		</tr>
		<tr>
			<th>팩스번호</th>
			<td>${buyer.buyer_fax }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${buyer.buyer_mail }</td>
		</tr>
		<tr>
			<th>담당자명</th>
			<td>${buyer.buyer_charger }</td>
		</tr>
		<tr>
			<th>내선번호</th>
			<td>${buyer.buyer_telext }</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:url value="/buyer/buyerUpdate.do" var="buyerUpdateURL">
					<c:param name="what" value="${buyer.buyer_id }"/>
				</c:url>
				<a class="btn btn-success byAjax mr-2" href="${buyerUpdateURL }">수정</a>
				<a class="btn btn-secondary byAjax mr-2" href="<c:url value="/buyer/buyerList.do"/>">목록으로</a>
				<input type="button" value="뒤로가기" class="btn btn-info" 
					onclick="history.back();"
				/>
			</td>
		</tr>
		<tr>
			<th>거래품목</th>
			<td>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>품목명</th>
							<th>구매가</th>
							<th>판매가</th>
							<th>마일리지</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="prodList" value="${buyer.prodList }" />
						<c:choose>
							<c:when test="${not empty prodList }">
								<c:forEach items="${prodList }" var="prod">
									<tr>
										<c:url value="/prod/prodView.do" var="prodViewURL">
											<c:param name="what" value="${prod.prod_id }"/>
										</c:url>
										<td>
											<a class="byAjax" href="${prodViewURL }">${prod.prod_name }</a>
										</td>
										<td>${prod.prod_cost }</td>
										<td>${prod.prod_price }</td>
										<td>${prod.prod_mileage }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="4"> 거래 품목 없음. </td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
