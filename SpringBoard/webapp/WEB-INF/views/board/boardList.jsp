<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<!-- board table -->
<h3 style="color: red;">게시판</h3>
	<table class="table table-sm">
		<thead>
			<tr>
				<th>
					<spring:message code="board.bo_no"/>
				</th>
				<th>
					<spring:message code="board.bo_title"/>
				</th>
				<th>
					<spring:message code="board.bo_writer"/>
				</th>
				<th>
					<spring:message code="board.bo_date"/>
				</th>
				<th>
					<spring:message code="board.bo_hit"/>
				</th>
			</tr>
		</thead>
		<tbody id="listBody">
		
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
				<nav id="pagingArea">${pagingVO.pagingHTML }</nav>&nbsp;
					<div class="form-inline" id="searchUI">
						<select name="searchType">
							<option value="all" ${pagingVO.searchVO.searchType eq 'all'?"selected":""}>전체</option>
							<option value="writer" ${pagingVO.searchVO.searchType eq 'writer'?"selected":""}>작성자</option>
							<option value="title" ${pagingVO.searchVO.searchType eq 'title'?"selected":""}>제목</option>
						</select>&nbsp;
						<input type="text" name="searchWord" value="${pagingVO.searchVO.searchWord }"/>&nbsp;
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>&nbsp;
						<input type="button" value="게시글 작성" id="insertBtn" class="btn btn-success"/>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
	
	
<form id="searchForm">
	<input type="hidden" name="page"/>
	<input type="hidden" name="searchType" value="${pagingVO.searchVO.searchWord }"/>
	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }"/>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/dynamicSelect.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/paging.js"></script>

<script type="text/javascript">
	var listBody = $("#listBody");
	var searchForm = $("#searchForm").paging({
		searchUI : "#searchUI",
		searchBtn : "#searchBtn",
		pagination : "#pagingArea",
		pageParam : "page",
		byAjax : true, 
		success : function(resp){
			let boardList = resp.dataList;
			let pagingHTML = resp.pagingHTML;
			let trTags = [];
			if(boardList.length>0){
			$.each(boardList, function(idx,board){
				let trTag = $("<tr>").append(
				$("<td>").text(board.bo_no),
				$("<td>").html(
						$("<a>").attr("href", "${pageContext.request.contextPath }/board/"+board.bo_no)
								.html(board.bo_title)													
					)
					, $("<td>").text(board.bo_writer)
					, $("<td>").text(board.bo_date)
					, $("<td>").text(board.bo_hit));
				trTags.push(trTag);
			});
			$("#pagingArea").html(pagingHTML);
			}else{
				trTags.push($("<tr>").html($("<td colspan='8'>").text("조건에 맞는 게시글이 없음.")));
				$("#pagingArea").empty();
			}
			listBody.html(trTags);
			listBody.data("currentpage", resp.currentPage);
			searchForm.find("[name='page']").val("");
		}
	}).submit();
	
	$("#insertBtn").on("click",function(){
		location.href="${pageContext.request.contextPath }/board/form.do";
	});
	
</script>























