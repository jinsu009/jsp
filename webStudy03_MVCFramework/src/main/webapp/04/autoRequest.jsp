<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
<!-- 		<meta http-equiv="Refresh" content="3; url=https://www.naver.com/" > -->
		<!-- 강제로 응답데이터 생성;  content: 3초마다 실행 -->
		<title>04/autoRequest.jsp/0512</title>
		
		<script
	  src="https://code.jquery.com/jquery-3.5.1.min.js"
	  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	  crossorigin="anonymous"></script>
	  
	  <script type="text/javascript">
	  	$(function(){
	  		let timeArea = $("#timeArea");
	  		let secArea = $("#secArea");
	  		$("#cancleBtn").on("click",function(){
				clearInterval(jobId);
				$(this).prop("disabled",true);
	  		});
	  		
// 	  		setTimeout(function(){
// 	  			location.reload(); 	
// 	  		},3000);
	  		
	  		let initTime = 5;
	  		
	  		let jobId = setInterval(function(){
	  			secArea.text(--initTime);
	  			$.ajax({
					url : "<%=request.getContextPath()%>/getServerTime.do", 
					method : "get",
// 					dataType : "json", 
// 					dataType : "html", 
// 					dataType : "text", 
					dataType : "xml", 
					// Accept :
					// html > text/html, Content-Type:text/html
					// json >  application/json 
					success : function(resp) {
//	 						alert(resp.time);
// 						timeArea.text(resp.time); // json
// 						timeArea.html(resp); // html
// 						timeArea.text(resp); // text
						let serverTime = $(resp).find("time").text();
						timeArea.text(serverTime); // xml
					},
					error : function(errorResp) {
						console.log(errorResp.status + ":"
										+ errorResp.responseText);
					}
				});
	  		},1000);
	  	});
	  </script>
	</head>
	<body>
	<button id="cancleBtn" >비동기요청 취소</button>
	<p style="color:blue; font-size:10pt "> <span id="secArea" ></span> 초뒤에 네이버로 이동합니다.</p>
		<h3 style="color:blue">autoRequest</h3>
		<pre>
			: 서버사이드에서 주기적으로 갱신되는 데이터를 실시간으로 보여주기 위함 
			==============
			1. server side : Refresh Header
			
			시간의 타입이 long : millisecond, int : second
			
			lock(상황의 제어권한)가 없으면 동기 , lock가 있으면 비동기 
			
			 서버로부터 한번가져온 시간 > <%=new Date() %>
			서버의 현재 갱신되는 시간 > <span id="timeArea" style="color:blue; font-size:20pt "></span>
			<%--
				response.setIntHeader("Refresh", 1);
			--%>
			
			2. client side	 : 	
				1) JavaScript의 스케쥴링 함수
					- setTimeout : 일정시간동안 지연시켰다가 단 한번 실행. 
					- setInterval : 일정시간을 주기로 계속 반복되는 실행.
				2) html meta 태그 사용 
		</pre>
	</body>
</html>