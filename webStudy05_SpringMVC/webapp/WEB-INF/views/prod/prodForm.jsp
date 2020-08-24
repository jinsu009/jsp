<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> prodForm.jsp / prod 수정</title>
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
	
<style type="text/css">
	.error{color:red}
</style>
	
</head>

<body>

<form:form modelAttribute="prodvo" action="${cPath }${currentAction }" method="post" enctype="multipart/form-data">

	<c:if test="${not empty prodvo.prod_id }">
		<input type="hidden" name="_method" value="put"/>
		<!-- 비공식적으로 put 메소드를 사용하기 위해  -->
	</c:if>
	
<input class="form-control" type="hidden" name="currentPage" value="${param.currentPage }"/>
<table class="table table-bordered">
<!-- notnull -  -->
	<tbody>
		<tr>
			<th>prod_id(nn)</th>
			<td>
				<input type="text" name="prod_id" value="${prodvo.prod_id }" placeholder="아이디생성공간"  readonly="readonly"/>
				<form:errors path="prod_id" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_name(nn)</th>
			<td>
				<input type="text" name="prod_name" value="${prodvo.prod_name }"   />
				<form:errors path="prod_name" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_lgu(nn)</th>
			<td>
				<select class="dynamicElement" name="prod_lgu" data-url="<c:url value='/prod/getLprodList.do' />">
					<option value>상품분류</option>
				</select>
				<form:errors path="prod_lgu" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_buyer(nn)</th>
			<td>
				<select class="dynamicElement" name="prod_buyer" data-url="<c:url value='/prod/getBuyerList.do' />">
					<option value="">거래처</option>
				</select>
				<form:errors path="prod_buyer" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_cost(nn)</th>
			<td>
				<input type="text" name="prod_cost"  value="${prodvo.prod_cost }"   />
				<form:errors path="prod_cost" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_price(nn)</th>
			<td>
				<input type="text" name="prod_price" value="${prodvo.prod_price }" />
				<form:errors path="prod_price" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_sale(nn)</th>
			<td>
				<input type="text" name="prod_sale" value="${prodvo.prod_sale }"  />
				<form:errors path="prod_sale" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_outline(nn)</th>
			<td>
				<input type="text" name="prod_outline" value="${prodvo.prod_outline }"  />
				<form:errors path="prod_outline" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_detail</th>
			<td>
				<input type="text" name="prod_detail" value="${prodvo.prod_detail }" />
				<form:errors path="prod_detail" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_img(nn)</th>
			<td>
			<img src="<c:url value='/prodImages/${prodvo.prod_img }'/>">
				<input type="file" name="prod_image" value="${prodvo.prod_img }" />
				<form:errors path="prod_image" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_totalstock(nn)</th>
			<td>
				<input type="text" name="prod_totalstock" value="${prodvo.prod_totalstock }"  />
				<form:errors path="prod_totalstock" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_insdate</th>
			<td>
				<input type="date" name="prod_insdate" value="${prodvo.prod_insdate }" />
				<form:errors path="prod_insdate" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_properstock(nn)</th>
			<td>
				<input type="text" name="prod_properstock" value="${prodvo.prod_properstock }"  />
				<form:errors path="prod_properstock" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_size</th>
			<td>
				<input type="text" name="prod_size" value="${prodvo.prod_size }" />
				<form:errors path="prod_size" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_color</th>
			<td>
				<input type="text" name="prod_color" value="${prodvo.prod_color }" />
				<form:errors path="prod_color" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>prod_delivery</th>
			<td>
				<input type="text" name="prod_delivery" value="${prodvo.prod_delivery }" />
				<form:errors path="prod_delivery" element="span"  cssClass="error"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" class="btn btn-success mr-2" value="저장" />
				<input type="reset" class="btn btn-warning mr-2" value="취소" />
				<input type="button" class="btn btn-secondary mr-2" value="뒤로가기" 
					onclick="history.back();"/>
				<input type="button" class="btn btn-secondary" value="목록으로" 
					onclick="location.href='${pageContext.request.contextPath}/prod/prodList.do';"
				/>
			</td>
		</tr>
	</tbody>
</table>
</form:form>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/dynamicSelect.js?time=${System.currentTimeMillis()}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/paging.js?time=${System.currentTimeMillis()}"></script>
<script type="text/javascript">
	var optionPtrn = "<option value='%V' %S>%T</option>";
	
	var prod_lguTag = $("select[name='prod_lgu']").data("success", function(resp){
		var html = "";
		$.each(resp, function(idx,lprod){
			html += optionPtrn
					.replace("%V", lprod.lprod_gu)
					.replace("%T",lprod.lprod_nm)
					.replace("%S", lprod.lprod_gu == "${prodvo.prod_lgu}"?"selected":"");
		});
		prod_lguTag.append(html);
	}).on("change", function(){
		let prod_lgu = $(this).val()
		prod_buyerTag.trigger("renew",{
			prod_lgu : prod_lgu
		});
	});
	
	var prod_buyerTag = $("select[name='prod_buyer']").data("success",function(resp){
		var html = "<option value>거래처</option>";
		$.each(resp, function(idx, buyer){
			html += optionPtrn
				.replace("%V",buyer.buyer_id)
				.replace("%T",buyer.buyer_name)
				.replace("%S",buyer.buyer_id == "${prodvo.prod_buyer}"?"selected":"");
		});
		prod_buyerTag.html(html);
	});
	
	$(".dynamicElement").dynamicSelect();
</script>
</body>
</html>