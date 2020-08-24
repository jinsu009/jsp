<%@page import="java.util.List"%>
<%@page import="homework.vo.BuyerVO"%>
<%@page import="homework.vo.PagingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>buyer/buyerList.jsp/0525</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/jquery-ui-1.12.1/jquery-ui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css" >
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>  
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">회원 정보 상세 조회</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
      	
        <button type="button" class="btn btn-secondary" data-dismiss="modal">save</button>
        <button type="button" class="btn btn-primary">닫기</button>
      </div>
    </div>
  </div>
</div>

	<table border="1">
		<thead>
			<tr>
				<th>buyer_id</th>
				<th>buyer_name</th>
				<th>buyer_bank</th>
				<th>buyer_mail</th>
				<th>buyer_comtel</th>
				<th>buyer_add1</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${pagingVO.dataList}" var="list">
			<tr data-toggle="modal" data-target="#exampleModal">
				<td class="idTag">${list.buyer_id }</td>
				<td>${list.buyer_name }</td>
				<td>${list.buyer_bank }</td>
				<td>${list.buyer_mail }</td>
				<td>${list.buyer_comtel }</td>
				<td>${list.buyer_add1 }</td>
			</tr>
		</c:forEach>
			
		</tbody>
	<tfoot>
			<tr>
			<td colspan="6">
				<nav aria-label="...">
					${pagingVO.pagingHTML }
				</nav>
				<div class="form-inline" id="searchUI">
					<select name="searchType" class="form-control">
						<option value="all" ${pagingVO.searchVO.searchType eq 'all' ? "selected" :""}>전체</option>
						<option value="name" ${pagingVO.searchVO.searchType eq 'name' ? "selected" :""}>거래처명</option>
						<option value="address" ${pagingVO.searchVO.searchType eq 'address' ? "selected" :""}>거래처 지역</option>
					</select>
					<input type="text" class="form-control" name="searchWord"
					value="${pagingVO.searchVO.searchWord}"	/>
					<input class="btn btn-success" type="button" value="검색" id="searchBtn"/>
					&nbsp;
					<a href="${pagaContext.request.contextPath }/buyer/insertBuyer.do" style="color:green">buyer등록</a>
				</div>
				</td>
			</tr>
		</tfoot>
	</table>
	<form id="searchForm">
		<input type="text" name="page"/>
		<input type="text" name="searchType" value="${pagingVO.searchVO.searchWord}"/>
		<input type="text" name="searchWord" value="${pagingVO.searchVO.searchWord}"/>
	</form>
	<script type="text/javascript">
	$("tbody").on("click","tr",function(){
		var sampleModal = $("#exampleModal").on("hidden.bs.modal",function(){
			$(this).find(".modal-body").empty();
		}).on("show.bs.modal",function(event){
			console.log(000)
			let idTag = $(event.relatedTarget).find("td:nth(0)");
			console.log(idTag)
			let where = idTag.text();
			$.ajax({
				url : "${pageContext.request.contextPath }/buyer/buyerView.do",
				data : {
					where : where
				},
				method : "get",
				dataType : "html",
				// Accept : 
				// html > text/html, Content-Type:text/html
				// json >  application/json 
				success : function(resp) {
					sampleModal.find(".modal-body").html(resp);
				},
				error : function(errorResp) {
					console.log(errorResp.status + ":"+ errorResp.responseText);
				}
			});
		}).css({cursor:"pointer"});
	});
	
		var searchForm = $("#searchForm");
		var searchUI = $("#searchUI");
		$(".pagination").on("click","a",function(event){
			event.preventDefault(); // 올바르지 않은 텍스트가 입력란에 입력되는것을 막는다.
			let page = $(this).data("page");
			searchForm.find("[name='page']").val(page);
			searchForm.submit();
		});
		$("#searchBtn").on("click",function(){
			let children = searchUI.children(":input");
// 			console.log(children);
			$(children).each(function(idx, input){
				let name = $(this).attr("name");
				if(name){
					let value = $(this).val();
					searchForm.find("[name='"+name+"']").val(value);
				}
			});
			searchForm.submit();
		});
	</script>
</body>
</html>