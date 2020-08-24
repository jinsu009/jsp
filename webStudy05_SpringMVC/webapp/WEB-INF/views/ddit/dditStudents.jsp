<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.DDITStudentVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<h3> 등록된 모든 학생 조회 </h3>
		<a href="${pageContext.request.contextPath }/ddit/regist.do">신규</a>
		<table>
			<thead>
				<tr>
					<th>학번</th>
					<th>이름</th>
					<th>생일</th>
					<th>나이</th>
					<th>성별</th>
					<th>학교</th>
					<th>경력</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${pagingVO.dataList}" var="vo" varStatus="vs">
				<c:choose>
					<c:when test="${lastStudent eq pagingVO  }">
						<c:set var="clzName" value="pink"/>
					</c:when>
					<c:when test="">
						<c:set var="clzName" value="normal"/>
					</c:when>
				</c:choose>
				<tr class="${clzName }">
					<td>${vo.code}</td>
					<td><a href="${pageContext.request.contextPath }/ddit/studentView.do?code=${vo.code}">${vo.name}</a></td>
					<td>${vo.birthday}</td>
					<td>${vo.age}</td>
					<td>${vo.gender}</td>
					<td>${vo.grade}</td>
					<td>${vo.career}</td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
			<tr>
				<td colspan="7">
					<nav aria-label="...">
						${pagingVO.pagingHTML }
					</nav>
					<div id="searchUI">
						<select>
							<option value="all" ${pagingVO.searchVO.searchType eq 'all' ? "selected" : ""}>전체</option>
							<option value="name" ${pagingVO.searchVO.searchType eq 'name' ? "selected" : ""}>이름</option>
							<option value="career" ${pagingVO.searchVO.searchType eq 'career' ? "selected" : ""}>경력</option>
						</select>
						<input type="text" name="searchWord" value="${pagingVO.searchVO.searchWord}"/>
						<input type="button" value="검색" id="searchBtn"/>
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
