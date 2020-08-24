<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileUploadForm.jsp/0603</title>
</head>
<body>
<h3>파일업로드</h3>
<pre>
	§ 파일 업로드 처리 단계
	1. 클라이언트 사이드 
		1) form 구성 (method="post" enctype="multipart/form-data")
		2) input type="file" accept = "mime"
		* enctype body 영역의 데이터 mime 결정
		* multipart ? 입력 태그의 갯수 만큼 body영역이 여러개 part로 분리된다. 
	2. 서버 사이드 (servlet 3.0 이후)
		1) Multipart-config 설정(part API 사용가능해짐) 을 가진 Servlet필요 - web.xml
		2) Part request.getPart(part_name), Collection&lt;Part&gt; request.getParts()
		* part_name 은 input name = "part_name"으로 결정
		* 데이터를 입력하지 않아도 비어있는 part는 형성된다. 
		* 서버사이드에서 가지고 놀때는 일반데이터와 파일데이터에 대한 분리처리가 필요하다. 
</pre>
	<form action="${pageContext.request.contextPath }/upload" method="post" enctype="multipart/form-data">
		<ul>
			<li>
				<input type="text" name="uploader" placeholder="업로더"/>
			</li>
			<li>
<!-- 				accept : 이미지가아닌 다른 파일은 선택할수 없도록 막음 -->
				<input type="file" name="uploadFile" multiple accept="image/*"/>
				<input type="submit" name="" value="업로드"/>
			</li>
		</ul>
	</form>
	<c:if test="${not empty sessionScope.fileList }">
		<c:forEach items="${fileList }" var="file">
			<img src="<c:url value='/images/${file.name }'/>" />
		</c:forEach>
	</c:if>
</body>
</html>