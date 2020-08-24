<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
	.yellow{
		background-color: yellow;
	}
</style>
<div class="table-responsive-xl">
<h4>등록된 모든 학생 조회</h4>
<table class="table table-bordered">
	<thead class="thead-dark">
		<tr>
			<th>순번</th>
			<th>학번</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
			<th>경력</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="allStudents" value="${pagingVO.dataList }" />
		<c:choose>
			<c:when test="${not empty allStudents }">
				<c:forEach items="${allStudents }" var="student">
					<c:set var="clzName" value="${student eq sessionScope.lastStudent ? 'yellow':'normal' }" />
					<tr class="${clzName }">
						<td>${student.rnum }</td>
						<td>${student.code }</td>
						<td><a class="byAjax" href="${pageContext.request.contextPath }/ddit/studentView.do?code=${student.code }">${student.name }</a></td>
						<td>${student.age }</td>
						<td>${student.gen }</td>
						<td>${student.career }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr><td class="col">
					조건에 맞는 학생이 없음.
				</td></tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
	            <div class="card-footer d-block d-md-flex align-items-center d-print-none">
	                <div class="form-inline" id="searchUI">
						<select name="searchType" class="form-control mr-2">
							<option value="all" ${param.searchType eq 'all'?"selected":"" }>전체</option>
							<option value="name" ${param.searchType eq 'name'?"selected":"" }>이름</option>
							<option value="career" ${param.searchType eq 'career'?"selected":"" }>경력</option>
						</select>
						<input type="text" class="form-control mr-2" name="searchWord"
							value="${pagingVO.searchVO.searchWord }"
						>
						<input class="btn btn-success mr-2" type="button" value="검색" id="searchBtn">
						<a class="btn btn-info byAjax" href="${pageContext.request.contextPath }/ddit/regist.do">신규등록</a>
					</div>
	                <nav class="d-flex ml-md-auto d-print-none" aria-label="Pagination">
	                	${pagingVO.pagingHTML }
	                </nav>
	    		</div>	
			</td>
		</tr>
	</tfoot>
</table>
</div>
<!-- 이름과 경력사항 두가지 검색조건에 대한 처리 + 페이징 -->
<form id="searchForm" action="<c:url value='/ddit/dditStudents.do'/>">
	<input type="hidden" name="page" />
	<input type="hidden" name="searchType" value="${param.searchType }"/>
	<input type="hidden" name="searchWord" value="${param.searchWord }"/>
</form>
<script type="text/javascript">
	var searchForm = $("#searchForm");
	var searchUI = $("#searchUI");
	$(".pagination").on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
	});
	$("#searchBtn").on("click", function(){
		let children = searchUI.children(":input");
// 		console.log(children);
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









