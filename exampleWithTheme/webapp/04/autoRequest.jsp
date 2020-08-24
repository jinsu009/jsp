<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="5;url=https://www.naver.com"> -->
<title>04/autoRequest.jsp</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		let timeArea = $("#timeArea");
		let secArea = $("#secArea");
		$("#cancelBtn").on("click", function(){
			clearInterval(jobId);
			$(this).prop("disabled", true);
		});
// 		setTimeout(function(){
// 			location.reload();
// 		}, 3000);
		let initTime = 5;
		let jobId = setInterval(function(){
			secArea.text(--initTime);
			$.ajax({
				url : "<%=request.getContextPath() %>/getServerTime.do",
				method : "get",
				dataType : "xml", // Accept:application/json, Content-Type:application/json
				success : function(resp) {
//	 				alert(resp.time);
// 					timeArea.text(resp.time); // json
// 					timeArea.html(resp); // html
// 					timeArea.text(resp); // text
					let serverTime = $(resp).find("time").text(); // xml
					timeArea.text(serverTime);
				},
				error : function(errorResp) {
					console.log(errorResp.status + ":" + errorResp.responseText);
				}
			});
		}, 1000);
	});
</script>  
</head>
<body>
<button id="cancelBtn">비동기 요청 취소</button>
<span id="secArea"></span> 초 뒤에 네이버로 이동함.
<h4> auto request </h4>
<pre>
	: 서버 사이드에서 주기적으로 갱신되는 데이터를 실시간으로 부여주기 위함.
	1. server side : Refresh header 
	 서버로부터 한번 가져온 시간 :  <%=new Date() %>
	 서버의 현재 갱신되는 시간 : <span id="timeArea"></span>
	<%--
		response.setIntHeader("Refresh", 1);
	--%>
	2. client side
		1) javascript의 스케쥴링 함수 
			setTimeout : 일정시간 동안 지연시켰다가 단 한번 실행.
			setInterval : 일정시간을 주기로 계속 반복되는 실행.
		2) html meta 태그 사용.	
</pre>
</body>
</html>









