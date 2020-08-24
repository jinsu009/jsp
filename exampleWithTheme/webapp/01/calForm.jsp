<%@page import="kr.or.ddit.enums.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div style="background-color: yellow;">
	최초의 요청에 포함됐던 파라미터 : <%=request.getParameter("param") %>
</div>
<!-- 사칙연산기 생성. -->
<!-- 1. 피연산자 2개, 연산자 1개 파라미터 입력 -->
<!-- 2. 피연산자와 연산자를 확보하고, 연산자(4칙)에 따라 연산 수행. -->
<form action="<%=request.getContextPath() %>/01/calculate.do">
<input type="number" name="left" />
<%
	OperatorType[] types = OperatorType.values();
	for(OperatorType op : types){
		String opName = op.name();
		char sign = op.getSign();
		%>
	<input type="radio" name="operator" value="<%=opName%>"/><%=sign %>
		<%
	}
%>
<input type="number" name="right" />
<input type="submit" value="=" />
</form>
<div id="resultArea">

</div>
<script type="text/javascript">
	var resultArea = $("#resultArea");
	$("form").on("submit", function(event){
		var url = $(this).attr("action");
		var data = $(this).serialize();
		alert(data);
		// cancel
		event.preventDefault();
		$.ajax({
			url:url,
			method:"get",
			data:data, // prarameter
			dataType:"html",
			success:function(resp){
				resultArea.html( resp );
			},
			error:function(errorResp){
				
			}
		});
		return false;
	});
</script>










