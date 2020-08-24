<%@page import="kr.or.ddit.enums.OperatorType.Operator"%>
<%@page import="kr.or.ddit.enums.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

	<div style="background-color: teal;">
		최초의 요청에 포함됐던 파라미터  <%=request.getParameter("param") %>
	
	</div>
	
		<h1>사칙연산기 생성 .. 01/calForm.jsp</h1>
		<pre>
			1. 피연산자 2개, 연산자 1개의 파라미터 입력
			2. 피연산자와 연산자를 확보하고, 연산자(4칙 연산)에 따라 연산을 수행
			3. 데이터를 입력받을수 있는 ui 
		</pre>
		<hr>
		<form action="<%=request.getContextPath() %>/01/calculate.do">
			<input type="number" name="left"/>
			
			<%
				// operator 넘겨주기 
				OperatorType[] types = OperatorType.values();
				for(OperatorType op : types){
					String opname = op.name();
					char sign = op.getSign();
					%>
					<input type="radio" name="operator" value="<%=opname%>"/><%=sign %>
					<%
				}
			%>
				
			<input type="number" name="right"/>
			<input type="submit" value="="/>
		</form>
		<hr>
		<div id="resultArea"></div>
		<hr>
		<script type="text/javascript">
		var resultArea = $("#resultArea");
			$("form").on("submit",function(event){
				var url = $(this).attr("action");
				var data = $(this).serialize();
				alert(data); //0508 수업주제 
				// callback 함수
				// cancle
				event.preventDefault(); // 화면전환을 막음 
				$.ajax({
					url : url,
// 					method:"post",
					method:"get",
					data : data, // 파라미터 정보가 세팅되어야한다. 
					dataType:"html", //응답데이터의 mime에 영향을 받는다.  
					success:function(resp){
						resultArea.html(resp);
					},
					error:function(errorResp){
						
					}
				});
				
				return false;
			});
		</script>
		