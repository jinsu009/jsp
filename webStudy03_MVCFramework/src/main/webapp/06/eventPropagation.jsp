<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>06/eventPropagation.jsp/0515</title>
		<style type="text/css">
			div{ padding : 10px; border: 1px solid black; }
		</style>
		
		<script
		  src="https://code.jquery.com/jquery-3.5.1.min.js"
		  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		  crossorigin="anonymous"></script>
	</head>
	<body>
		<h4>이벤트 전파</h4>
		<pre>
		- Bubbling : 최하위 엘리먼트 부터 최상위에 있는 엘리먼트로 이벤트 전파 
		
		- Capturing : 최상위 엘리먼트 부터 최하위에 있는 엘리먼트로 이벤트 전파
			jquery 에서는 확인 할 수 없다 default로 bubbling 구조이기 때문에 
		</pre>
		
		<button id="newBtn">새로만들기</button>
		<div id="outer">
			<div id="middle">
				<div id="inner">
					여기 클릭
				</div>
			</div>
		</div>
	<script type="text/javascript">
	//-------capturing , bubbling
// 		var divs = document.querySelectorAll("div"); // 조건에 맞는 모든 엘리먼트 가져오기
// 		divs.forEach(function(div){
// 			// jquery each() 와 같다
// 			div.addEventListener("click", function(event){
// 				let id =this.id;
// 				alert(id);				
// 			},true);
// 			// false , null(default) : bubbling
// 			// true : capturing
// 		});
	//-------bubbling
// 		$("div").on("click", function(){
// 			let id = $(this).prop("id");
// 			alert(id);
// 		});		
	//-------bubbling : 
	$("#newBtn").on("click",function(){
		$("#inner").append($("<div>").prop("id","newInner").text("새로만든 div"))
		alert(divs.length); // 새로만들어진 div에서는 click 가 적용되지 않는다. 
	});
	var divs = $("body").on("click", "#inner",function(event){
			let id = $(this).prop("id");
// 			if(id=="middle"){ event.stopPropagation(); }
			alert(id);
		});	
	</script>
	</body>
</html>