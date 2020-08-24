
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<table class="table table-bordered">
	<tr>
		<th>상품코드</th>
		<td>${prod.prod_id }</td>
	</tr>
	<tr>
		<th>상품명</th>
		<td>${prod.prod_name }</td>
	</tr>
	<tr>
		<th>상품분류</th>
		<td>${prod.prod_lgu }</td>
	</tr>
	<tr>
		<th>거래처</th>
		<td>${prod.buyer.buyer_name }</td>
	</tr>
	<tr>
		<th>구매가</th>
		<td>${prod.prod_cost }</td>
	</tr>
	<tr>
		<th>판매가</th>
		<td>${prod.prod_price }</td>
	</tr>
	<tr>
		<th>세일가</th>
		<td>${prod.prod_sale }</td>
	</tr>
	<tr>
		<th>정보</th>
		<td>${prod.prod_outline }</td>
	</tr>
	<tr>
		<th>상세정보</th>
		<td>${prod.prod_detail }</td>
	</tr>
	<tr>
		<th>상품이미지</th>
		<td>${prod.prod_img }</td>
	</tr>
	<tr>
		<th>재고</th>
		<td>${prod.prod_totalstock }</td>
	</tr>
	<tr>
		<th>입고일</th>
		<td>${prod.prod_insdate }</td>
	</tr>
	<tr>
		<th>적정재고</th>
		<td>${prod.prod_properstock }</td>
	</tr>
	<tr>
		<th>크기</th>
		<td>${prod.prod_size }</td>
	</tr>
	<tr>
		<th>색상</th>
		<td>${prod.prod_color }</td>
	</tr>
	<tr>
		<th>배송방법</th>
		<td>${prod.prod_delivery }</td>
	</tr>
	<tr>
		<th>단위</th>
		<td>${prod.prod_unit }</td>
	</tr>
	<tr>
		<th>입고량</th>
		<td>${prod.prod_qtyin }</td>
	</tr>
	<tr>
		<th>판매량</th>
		<td>${prod.prod_qtysale }</td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td>${prod.prod_mileage }</td>
	</tr>
	<tr>
		<td colspan="2">
			<a class="btn btn-primary mr-2 byAjax" href="${pageContext.request.contextPath }/prod/prodUpdate.do?what=${prod.prod_id }">수정</a> 
			<a class="btn btn-secondary mr-2 byAjax" href="<c:url value='/prod/prodList.do'/>">목록으로</a> 
			<input type="button" class="btn btn-secondary mr-2" value="뒤로가기" 
				onclick="history.back();"
			/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>회원아이디</th>
						<th>회원명</th>
						<th>휴대폰</th>
						<th>이메일</th>
						<th>마일리지</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty prod.memberList }">
							<c:forEach var="member" items="${prod.memberList }">
								<tr>
									<td>${member.mem_id }</td>
									<td>${member.mem_name }</td>
									<td>${member.mem_hp }</td>
									<td>${member.mem_mail }</td>
									<td>${member.mem_mileage }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5">구매자가 없음.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</td>
	</tr>
</table>




