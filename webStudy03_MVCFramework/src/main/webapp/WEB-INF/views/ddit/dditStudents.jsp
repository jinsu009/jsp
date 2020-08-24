<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.DDITStudentVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ddit/dditStudents.jsp/0512</title>
		<style type="text/css">
			table{ column-span: collapse; }
			td, th { border:1px solid black; }
			.pink { background-color: pink }
		</style>
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
	</head>
	<body>
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
				<%
// 					PagingVO<DDITStudentVO> pagingVO = (PagingVO)request.getAttribute("pagingVO");
// 					List<DDITStudentVO> allStudents = pagingVO.getDataList();
// 					int b = allStudents.size()-1;
// 					String c = allStudents.get(b).getCode();
// 					String d = "";
// 					DDITStudentVO lastStudent = (DDITStudentVO)session.getAttribute("lastStudent");
					// session 은 필요없어지면 지워야 한다. .. 새로고침하면 class 적용이 사라짐 
// 					session.removeAttribute("lastStudent");
// 					for(DDITStudentVO vo : allStudents)
// 					{
// 						String clzName = vo.equals(lastStudent) ?  "pink" : "normal";
						// 가장 마지막에 추가된학생 배경색 넣어주기
// 						String a = vo.getCode();
// 						if(a.equals(c))	{ d="pink"; }
						%>
<%-- 							<tr class="<%=d %>"> --%>
						<%
// 					}
				%>
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
	</body>
</html>