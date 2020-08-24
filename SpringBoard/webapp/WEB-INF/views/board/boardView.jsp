<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table  class="table table-bordered">
	<thead>
		<tr>
			<th>bo_title</th>
			<td>${board.bo_title }</td>
		</tr>
		<tr>
			<th>bo_writer</th>
			<td>${board.bo_writer }</td>
		</tr>
		<tr>
			<th>bo_date</th>
			<td>${board.bo_date }</td>
		</tr>
		<tr>
			<th>bo_hit</th>
			<td>${board.bo_hit }</td>
		</tr>
		<tr>
			<th>bo_ip</th>
			<td>${board.bo_ip }</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:forEach items="${board.attatchList }" var="attatch" varStatus="vs">
					<a href="<c:url value='/board/file/${attatch.att_no }'/>">${attatch.att_filename }</a> ${not vs.last?"&nbsp;":"" }
				</c:forEach>
			</td>
			<!--get 방식을 이용해서파일 다운로드 받기  -->
		</tr>	
	</thead>
	<tbody>
		<tr>
			<th>bo_content</th>
			<td>${board.bo_content }</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="3">
			<input type="button" class="btn btn-success" value="수정하기" onclick="location.href='<c:url value="/board/${board.bo_no }/form"/>';" />
				<input type="button" class="btn btn-secondary" value="뒤로가기"onclick="history.back();" />
				<input type="button" class="btn btn-primary" value="목록으로" onclick="location.href='<c:url value='/board'/>';" />
				<c:url value="/board/form" var="childBoardURL">
					<c:param name="bo_parent" value="${board.bo_no }"/>
				</c:url>
				<input type="button" class="btn btn-primary" value="답글쓰기" onclick="location.href='${childBoardURL}';" />
				<input type="button" class="btn btn-danger"  value="삭제" id="delBtn"/>
			</td>
		</tr>
	</tfoot>
</table>
<form method="post" action="${pageContext.request.contextPath }/board/${board.bo_no}" class="form-inline">
<!-- Modal -->
<div class="modal fade"  class="modal-dialog modal-xl" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">게시글 삭제</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      	<input type="hidden" name="_method" value="delete">
	  <div class="modal-body">
      	<input type="text" name="password" placeholder="비밀번호입력"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
        <button type="submit" class="btn btn-primary">삭제</button>
      </div>
    </div>
  </div>
</div>
</form>
<script type="text/javascript">
	$("#delBtn").on("click",function(){
		$("#deleteModal").modal("show");
	})
</script>