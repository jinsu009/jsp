<%@page import="homework.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@page import="homework.vo.BuyerVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>buyer/buyerView.jsp/0526</title>
</head>
<body>
<a href="${pageContext.request.contextPath }/buyer/updateBuyer.do?buyer_id=${buyVO.buyer_id }">수정하기</a>
	<table border="1">
		<tbody>
			<tr>
				<th>id</th>
				<td>${buyVO.buyer_id}</td>
			</tr>
			<tr>
				<th>name</th>
				<td>${buyVO.buyer_name}</td>
			</tr>
			<tr>
				<th>bank</th>
				<td>${buyVO.buyer_bank}</td>
			</tr>
			<tr>
				<th>add1</th>
				<td>${buyVO.buyer_add1}</td>
			</tr>
			<tr>
				<th>add2</th>
				<td>${buyVO.buyer_add2}</td>
			</tr>
			<tr>
				<th>prodlist</th>
				<td>
					<table>
						<thead>
							<tr>
								<th>prod_name</th>
								<th>prod_cost</th>
								<th>prod_price</th>
								<th>prod_sale</th>
							</tr>
						</thead>
						<tbody>
						<c:set var="prodList" value="${buyVO.prodList}" />
						<c:choose>
							<c:when test="${not empty prodList }">
								<c:forEach items="${prodList }" var ="prod">
									<tr>
										<td>${prod.prod_name}</td>
										<td>${prod.prod_cost}</td>
										<td>${prod.prod_price}</td>
										<td>${prod.prod_sale}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="4">목록이 없음</td>
								</tr>
							</c:otherwise>
						</c:choose>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	
</body>
</html>