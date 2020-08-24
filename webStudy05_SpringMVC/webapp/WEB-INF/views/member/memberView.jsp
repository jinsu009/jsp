<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<table class="table table-sm">
		<tbody>

			<tr>
				<th>아이디</th>
				<td>${memVO.mem_id }</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${memVO.mem_name }</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>${memVO.mem_bir }</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${memVO.mem_add1 }, ${memVO.mem_add2 }</td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td>${memVO.mem_hp }</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${memVO.mem_mail }</td>
			</tr>
			<tr>
				<th>직업</th>
				<td>${memVO.mem_job }</td>
			</tr>
			<tr>
				<th>취미</th>
				<td>${memVO.mem_like }</td>
			</tr>
			<tr>
				<th>기념일</th>
				<td>${memVO.mem_memorial}</td>
			</tr>
			<tr>
				<th>기념일 날짜</th>
				<td>${memVO.mem_memorialday }</td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>${memVO.mem_mileage }</td>
			</tr>
			<tr>
				<th>구매목록</th>
				<td>
					<table class="table table-sm">
						<thead>
							<tr>
								<th>상품코드</th>
								<th>상품명</th>
								<th>구매가</th>
								<th>판매가</th>
								<th>마일리지</th>
								<th>거래처</th>
							</tr>
						</thead>
						<tbody>
						<c:set var="prodList" value="${memVO.prodList }"/>
						<c:choose>
							<c:when test="${not empty prodList  }">
								<c:forEach items="${prodList }" var="prod">
							<tr>
								<td>${prod.prod_id }</td>
								<td>${prod.prod_name }</td>
								<td>${prod.prod_cost }</td>
								<td>${prod.prod_price }</td>
								<td>${prod.prod_mileage }</td>
								<td>${prod.buyer.buyer_name }</td>
							</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
							<tr>
								<td colspan="6">구매 목록이 없음</td>
							</tr>
							</c:otherwise>
						</c:choose>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
