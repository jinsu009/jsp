<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<div class="form-inline mb-2" id="searchUI">
	<select class="dynamicElement form-control mr-2" name="prod_lgu"
		data-url="<c:url value='/prod/getLprodList.do'/>"
	>
		<option value>상품분류</option>
		
	</select>
	<select class="dynamicElement form-control mr-2" name="prod_buyer"
		data-url="<c:url value='/prod/getBuyerList.do' />"
	>
		<option value="">거래처</option>
		
	</select>
	<input class="btn btn-success mr-2" type="button" value="검색" id="searchBtn">
	<a class="btn btn-primary byAjax" href="<c:url value='/prod/prodInsert.do'/>">신규등록</a>
</div>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>상품코드</th>
			<th>상품명</th>
			<th>거래처명</th>
			<th>분류명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>OUTLINE</th>
		</tr>
	</thead>
	<tbody id="listBody">
		<c:set var="prodList" value="${pagingVO.dataList }" />
		<c:if test="${not empty prodList }">
			<c:forEach items="${prodList }" var="prod">
				<tr data-prodid="${prod.prod_id }">
					<td>${prod.prod_id }</td>
					<td>${prod.prod_name }</td>
					<td>${prod.buyer.buyer_name }</td>
					<td>${prod.lprod_nm }</td>
					<td>${prod.prod_cost }</td>
					<td>${prod.prod_price }</td>
					<td>${prod.prod_outline }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty prodList }">
			<tr>
			<td class="col">조건에 맞는 상품이 없음.</td>
			</tr>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<nav id="pagingArea">
					${pagingVO.pagingHTML }
				</nav>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm" action="<c:url value='/prod/prodList.do'/>">
	<input type="hidden" name="page" />
	<input type="hidden" name="prod_lgu" value=""/>
	<input type="hidden" name="prod_buyer" value=""/>
</form>
<form id="viewForm" action="<c:url value='/prod/prodView.do' />">
	<input type="hidden" name="what" />
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dynamicSelect.js?time=${System.currentTimeMillis()}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/paging.js?time=${System.currentTimeMillis()}"></script>
<script type="text/javascript">
	var optionPtrn = "<option value='%V'>%T</option>";
	var prod_lguTag = $("select[name='prod_lgu']").data("success", function(resp){
		var html = "";
		$.each(resp, function(idx, lprod){
			html += optionPtrn.replace("%V", lprod.lprod_gu).replace("%T", lprod.lprod_nm);
		});
		prod_lguTag.append(html);
	}).on("change", function(){
		let prod_lgu = $(this).val();

		prod_buyerTag.trigger("renew", {
			prod_lgu : prod_lgu
		});
	});
	
	var prod_buyerTag = $("select[name='prod_buyer']").data("success", function(resp){
		var html = "<option value>거래처</option>";
		$.each(resp, function(idx, buyer){
			html += optionPtrn.replace("%V", buyer.buyer_id).replace("%T", buyer.buyer_name);
		});
		prod_buyerTag.html(html);
	});
	
	$(".dynamicElement").dynamicSelect();
	
	var searchForm = $("#searchForm").paging({
		searchUI:"#searchUI",
		searchBtn:"#searchBtn",
		pagination:"#pagingArea",
		pageParam:"page"
	});
	
	var viewForm = $("#viewForm");
	$("#listBody").on("click", "tr", function(){
		let what = $(this).data("prodid");
		viewForm.find("[name='what']").val(what);		
		viewForm.submit();
	});
</script>



