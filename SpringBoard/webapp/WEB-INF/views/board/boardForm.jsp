<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/ckeditor/ckeditor.js">

	
</script>

<form:form id="boardForm" modelAttribute="board"  method="post" enctype="multipart/form-data" class="w-100"
	action="${pageContext.request.contextPath }${currentAction }">
	
	<c:if test="${not empty methodType }">
		<input type="hidden" name="_method" value="${methodType }">
	</c:if>
	
<table  class="table table-bordered">
	<input type="hidden" name="bo_no" value="${board.bo_no }">
	<input type="hidden" name="bo_parent" value="${board.bo_parent }">
	<thead>
		<tr>
			<th>bo_title</th>
			<td><input type="text" value="${board.bo_title }" name="bo_title"/></td>
		</tr>
		<tr>
			<th>bo_writer</th>
			<td><input type="text" value="${board.bo_writer }" name="bo_writer"/></td>
		</tr>
		<tr>
			<th>bo_pass</th>
			<td><input type="text" value="${board.bo_pass }" name="bo_pass"/></td>
		</tr>
		<tr>
			<th>bo_ip</th>
			<td><input type="text" value="${pageContext.request.remoteAddr  }" name="bo_ip" readonly/></td>
		</tr>
		
		<c:if test="${not empty board.attatchList }">
			<tr>
				<th>저장된 파일</th>
				<td>
					<c:forEach items="${board.attatchList }" var="attatch" varStatus="vs">
						<span class="eachAttatch">${attatch.att_filename }
							<span class="delBtn" data-attno="${attatch.att_no }">[삭제]</span>
						${not vs.last?"&nbsp;":"" }</span>
					</c:forEach>
				</td>
			</tr>
		</c:if>
		
		<tr>
			<th>bo_files</th>
			<td>
				<input type="file" name="bo_files" />
				<input type="file" name="bo_files" />
				<input type="file" name="bo_files" />
			</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>bo_content</th>
			<td><textarea id="bo_content" rows="5" cols="100" name="bo_content">${board.bo_content }</textarea></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
		<td colspan="2">
			<input type="submit" class="btn btn-success mr-2" value="저장" />
			<input type="reset" class="btn btn-warning mr-2" value="취소" />
			<input type="button" class="btn btn-secondary mr-2" value="뒤로가기" onclick="history.back();"/>
			<input type="button" class="btn btn-secondary" value="목록으로" onclick="location.href='<c:url value='/board'/>';"/>
		</td>
		</tr>
	</tfoot>
</table>
</form:form>

<script type="text/javascript">

// ui 적용
CKEDITOR.replace("bo_content", {
    // Adding drag and drop image upload.
    uploadUrl: '${pageContext.request.contextPath}/board/image?command=QuickUpload&type=Files&responseType=json',

    // Configure your file manager integration. This example uses CKFinder 3 for PHP.
    filebrowserImageUploadUrl: '${pageContext.request.contextPath}/board/image?command=QuickUpload&type=Images',
});


// 첨부파일 삭제 
	var boardForm = $("#boardForm");
	$(".delBtn").on("click",function(){
		let att_no = $(this).data("attno");
		boardForm.append(
			$("<input>").attr({
				type:"text",
				name : "deleteAttatches",
				value:att_no
			})		
		);
		$(this).parent("span:first").remove();
	});
</script>
