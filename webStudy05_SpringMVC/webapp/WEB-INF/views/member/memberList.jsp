<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- Modal -->
<div class="modal fade"  class="modal-dialog modal-xl" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">회원 정보 상세 조회</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body"></div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
	<table class="table table-bordered table-dark">
		<thead>
			<tr>
				<th>
					<spring:message code="member.mem_id"/>
				</th>
				<th>
					<spring:message code="member.mem_name"/>
				</th>
				<th>
					<spring:message code="member.mem_hp"/>
				</th>
				<th>
					<spring:message code="member.mem_mail"/>
				</th>
				<th>
					<spring:message code="member.mem_add1"/>
				</th>
				<th>
					<spring:message code="member.mem_mileage"/>
				</th>
			</tr>
		</thead>
		<tbody id="listBody">

		</tbody>
		<tfoot>
			<tr>
			<td colspan="6">
				<nav id="pagingArea"> ${pagingVO.pagingHTML } </nav>
			
				<div class="form-inline" id="searchUI">
					<select style="margin:10px" name="searchType" class="form-control">
						<option value="all" ${pagingVO.searchVO.searchType eq 'all' ? "selected" :""}>전체</option>
						<option value="name" ${pagingVO.searchVO.searchType eq 'name' ? "selected" :""}>이름</option>
						<option value="address" ${pagingVO.searchVO.searchType eq 'address' ? "selected" :""}>지역</option>
					</select>
					<input style="margin:10px" type="text" class="form-control" name="searchWord"
					value="${pagingVO.searchVO.searchWord}"
					/>
					<input style="margin:10px" class="btn btn-info" type="button" value="검색" id="searchBtn"/>
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
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/dynamicSelect.js?time=${System.currentTimeMillis()}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/paging.js?time=${System.currentTimeMillis()}"></script>
	<script type="text/javascript">
	
	//  $("#exampleModal") :조건에 맞는 엘리먼트 객체를 받아와서 html로 바꿔서 html의 함수를 사용할수 있도록 한다.
	
	
	var searchForm = $("#searchForm").paging({
		searchUI : "#searchUI",
		searchBtn : "#searchBtn",
		pagination : "#pagingArea",
		pageParam : "page",
		byAjax : true, 
		success : function(resp){
			let memList = resp.dataList;
			let pagingHTML = resp.pagingHTML;
			console.log(memList);
			let trTags = [];
				$.each(memList, function(idx,member){
					let trTag = $("<tr>").append(
						$("<td>").text(member.mem_id),
						$("<td>").text(member.mem_name),
						$("<td>").text(member.mem_hp),
						$("<td>").text(member.mem_mail),
						$("<td>").text(member.mem_add1),
						$("<td>").text(member.mem_mileage)
						).data("memid", member.mem_id);
						trTags.push(trTag);
				});
			
			$("#listBody").html(trTags);
			listBody.data("currentpage", resp.currentPage);
			$("#pagingArea").html(pagingHTML);
			searchForm.find("[name='page']").val("");
		}
	});
	
	searchForm.submit();
	
	var sampleModal = $("#exampleModal").modal({
		show : false		
	}).on("hidden.bs.modal",function(){
		sampleModal.find(".modal-body").empty();
		sampleModal.data("memid","");
	});
	
	function memberView(mem_id){
		$.ajax({
			url : "<c:url value='/member/memberView.do'/>",
			data : {
				who : mem_id
			},
			dataType : "html",
			// Accept : 
			// html > text/html, Content-Type:text/html
			// json >  application/json 
			success : function(resp) {
//				$(".modal-body").empty();
//				$(".modal-body").append(resp);
				sampleModal.find(".modal-body").html(resp);
				sampleModal.data("memid",mem_id);
				sampleModal.modal("show");
			},
			error : function(errorResp) {
				console.log(errorResp.status + ":"+ errorResp.responseText);
			}
		});
	}
	
	var listBody = $("#listBody").on("click","tr",	function() {
		let mem_id = $(this).data("memid");
		memberView(mem_id);
		}).css({
		cursor : "pointer"
	});
	
	
	// tr클릭시 회원의 상세 페이지로 이동(05.26)
		$("tbody").on("click","tr",function(){
			sampleModal.modal("show");
		});
		
	</script>
