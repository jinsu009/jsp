<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/ webSocketEcho</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
</head>
<body>
<h2>웹 소켓프로토콜을 이용한 에코 테스트 </h2>
<input type="text" id="url" value=""/>
<input type="button" value="connect" id="connect"/>
<input type="button" value="disConnect" id="disConnect"/>

<hr/>
<input type="text" id="msg" /><input type="button" value="send" id="send"/>
<div id="msgArea">

</div>
<script type="text/javascript">

	const protocol = location.protocol == "http:"?"ws:":"wss:";
	const domain = location.hostname;
	const port = location.port != ""?":"+location.port:"";
	const address = protocol+"//"+domain+port+"${cPath}/echo";
	$("#url").val(address);

	var ws = null;
	$("#connect").on("click",function(){
		let add = $("#url").val();
		ws = new WebSocket(add);
		 //연결에 성공하면 
		ws.onopen = function(event){
			 console.log(event);
		 }
		ws.onclose = function(event){
			 console.log(event);
		 }
		ws.onmessage = function(event){
			 let msg = event.data;
			 $("#msgArea").append($("<p>").text('receive message : ' + msg));
		 }
		ws.onerror = function(event){
			 console.log(event);
		 }
	});
	
	$("#send").on("click",function(){
		let msg = $("#msg").val();
		ws.send(msg);
		$("#msgArea").append($("<p>").text('send message : ' + msg));
	});
	$("#disConnect").on("click",function(){
		ws.close();
	})
</script>
</body>
</html>