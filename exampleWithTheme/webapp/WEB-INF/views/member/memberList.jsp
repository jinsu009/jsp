<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
       <!-- Breadcrumb -->
       <nav class="d-none d-md-block" aria-label="breadcrumb">
           <ol class="breadcrumb">
               <li class="breadcrumb-item">
                   <a href="#">Users</a>
               </li>
               <li class="breadcrumb-item active" aria-current="page">All Users</li>
           </ol>
       </nav>
       <!-- End Breadcrumb -->

       <div class="mb-3 mb-md-4 d-flex justify-content-between">
           <div class="h3 mb-0">Users</div>
       </div>


   	 <!-- Users -->
       <div class="table-responsive-xl">
           <table class="table text-nowrap mb-0">
               <thead>
               <tr>
                   <th class="font-weight-semi-bold border-top-0 py-2">#</th>
                   <th class="font-weight-semi-bold border-top-0 py-2">아이디</th>
                   <th class="font-weight-semi-bold border-top-0 py-2">이름</th>
                   <th class="font-weight-semi-bold border-top-0 py-2">연락처</th>
                   <th class="font-weight-semi-bold border-top-0 py-2">이메일</th>
                   <th class="font-weight-semi-bold border-top-0 py-2">지역</th>
                   <th class="font-weight-semi-bold border-top-0 py-2">마일리지</th>
               </tr>
               </thead>
                   <tbody>
	                    <c:set value="${pagingVO.dataList }" var="memberList" />
						<c:if test="${not empty memberList }">
							<c:forEach items="${memberList }" var="member">
	                           <tr class="userList" data-memid="${member.mem_id }">
	                               <td class="py-3">${member.rnum }</td>
	                               <td class="align-middle py-3">
	                                   <div class="d-flex align-items-center">
	                                       <div class="position-relative mr-2">
	                                           <span class="indicator indicator-lg indicator-bordered-reverse indicator-top-left indicator-success rounded-circle"></span>
	                                           <span class="avatar-placeholder mr-md-2">${member.mem_id.substring(0, 1) }</span>
	                                       </div>
	                                       ${member.mem_id }
	                                   </div>
	                               </td>
	                               <td class="py-3">${member.mem_name }</td>
	                               <td class="py-3">${member.mem_hp }</td>
	                               <td class="py-3">${member.mem_mail }</td>
	                               <td class="py-3">${member.mem_add1 }</td>
	                               <td class="py-3">${member.mem_mileage }</td>
	                           </tr>
                      		</c:forEach>
						</c:if>
						<c:if test="${empty memberList }">
							<tr><td colspan="7"> 조건에 맞는 회원이 없음. </td></tr>
						</c:if>
                 </tbody>
            </table>
            <div class="card-footer d-block d-md-flex align-items-center d-print-none">
                <div class="form-inline" id="searchUI">
					<select name="searchType" class="form-control mr-2">
						<option value="all" ${pagingVO.searchVO.searchType eq 'all'?"selected":"" }>전체</option>
						<option value="name" ${pagingVO.searchVO.searchType eq 'name'?"selected":"" }>이름</option>
						<option value="address" ${pagingVO.searchVO.searchType eq 'address'?"selected":"" }>지역</option>
					</select>
					<input type="text" class="form-control mr-2" name="searchWord"
						value="${pagingVO.searchVO.searchWord }"
					>
					<input class="btn btn-success" type="button" value="검색" id="searchBtn">
				</div>
                <nav class="d-flex ml-md-auto d-print-none" aria-label="Pagination">
                	${pagingVO.pagingHTML }
                </nav>
    		</div>
        </div>
        <!-- End Users -->
<form id="viewForm" action="<c:url value='/member/memberView.do' />">
	<input type="hidden" name="who" />
</form>
<form id="searchForm" action="<c:url value='/member/memberList.do'/>">
	<input type="hidden" name="page" />
	<input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType }"/>
	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }"/>
</form>
<script type="text/javascript">
	var viewForm = $("#viewForm");
	$(".userList").on("click", function(){
// 		sampleModal.modal("show", this);
		let who = $(this).data("memid");
		console.log(who);
		viewForm.find("[name='who']").val(who);
		viewForm.submit();		
	});
	var sampleModal = $("#exampleModal").modal({
		backdrop:false,
		show:false
	}).on("hidden.bs.modal", function(){
		$(this).find(".modal-body").empty();
	}).on("show.bs.modal", function(event){
		console.log(event);
		let who = $(event.relatedTarget).data("memid");
		$.ajax({
			url : "${pageContext.request.contextPath }/member/memberView.do",
			data : {
				who:who
			},
			method : "get",
			dataType : "html", // Accept:application/json, Content-Type:application/json
			success : function(resp) {
				sampleModal.find(".modal-body").html(resp);
			},
			error : function(errorResp) {
				console.log(errorResp.status + ":" + errorResp.responseText);
			}
		});	
	}).css({cursor:"pointer"});

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