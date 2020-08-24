<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- 	상품정보수정화면으로 넘어감  -->
<!-- 	1. 수정버튼 눌렀을 때 수정 할 화면 받아오기  -->
<!-- 	2. 완료 됐을 때 수정된 정보 넘겨주기  -->
	<table class="table-active">
	<tbody class="table-primary">
		<tr><th>상품이름</th><td>${prodvo.prod_name }</td></tr>
		<tr><th>COST</th><td>${prodvo.prod_cost }</td></tr>
		<tr><th>PRICE</th><td>${prodvo.prod_price }</td></tr>
		<tr><th>SALE</th><td>${prodvo.prod_sale }</td></tr>
		<tr><th>outline</th><td>${prodvo.prod_outline }</td></tr>
		<tr><th>detail</th><td>${prodvo.prod_detail }</td></tr>
		<tr><th>IMG</th>
			<td>	
			<img src="<c:url value='/prodImages/${prodvo.prod_img }' />" style="width:200px; height:auto;" />
			</td>
		</tr>
		<tr><th>INSDATE</th><td>${prodvo.prod_insdate }</td></tr>
		<tr><th>SIZE</th><td>${prodvo.prod_size }</td></tr>
		<tr><th>COLOR</th><td>${prodvo.prod_color }</td></tr>
		<tr><th>DELIVERY</th><td>${prodvo.prod_delivery }</td></tr>
	</tbody>
	<tfoot>
	<c:set var="memberList" value="${prodvo.memberList }" />
	<c:choose>
		<c:when test="${not empty memberList }">
			<c:forEach items="${memberList }" var="mem">
			<tr class="table-success">
				<th>name</th>
				<th>bir</th>
				<th>zip</th>
				<th>add1</th>
				<th>add2</th>
				<th>hometel</th>
				<th>comtel</th>
				<th>hp</th>
				<th>mail</th>
				<th>job</th>
				<th>like</th>
				<th>memorial</th>
				<th>memorialday</th>
				<th>mileage</th>
				<th>delete</th>
			</tr>
			<tr>
				<td>${mem.mem_name }</td>
				<td>${mem.mem_bir }</td>
				<td>${mem.mem_zip }</td>
				<td>${mem.mem_add1 }</td>
				<td>${mem.mem_add2 }</td>
				<td>${mem.mem_hometel }</td>
				<td>${mem.mem_comtel }</td>
				<td>${mem.mem_hp }</td>
				<td>${mem.mem_mail }</td>
				<td>${mem.mem_job }</td>
				<td>${mem.mem_like }</td>
				<td>${mem.mem_memorial }</td>
				<td>${mem.mem_memorialday }</td>
				<td>${mem.mem_mileage }</td>
				<td>${mem.mem_delete }</td>
			</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td>구매 목록이 없음</td>
			</tr>
		</c:otherwise>
	</c:choose>
	</tfoot>
	</table>
<%-- 		<input class="btn btn-dark" type="button" value="수정" onclick="location.href='${pageContext.request.contextPath }/prod/prodUpdate.do?prod_id=${prodvo.prod_id}';"/>  --%>
