<%@page import="java.util.Arrays"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="kr.or.ddit.Constants"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	String selectedJson = null;
	if(cookies!=null){
		for(Cookie tmp : cookies){
			if(Constants.SELECTEDFILE.equals(tmp.getName())){
				selectedJson = URLDecoder.decode(tmp.getValue(), "UTF-8");
			}
		}
	}
	String[] savedArray = null; 
	if(selectedJson!=null){
		ObjectMapper mapper = new ObjectMapper();
		savedArray = mapper.readValue(selectedJson, String[].class);
	}
%>    
<!DOCTYPE html>
<html>
<head>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<script type='text/javascript'>
	$(function(){
		let resultArea = $("#resultArea");
		$("select[name='filename']").on("change", function(event){
			let filenames = $(this).val();
			console.log(filenames);		
			let imgTags = [];
			$(filenames).each(function(idx, filename){
				let imgTag = $("<img>").attr({
								src:"<%=request.getContextPath() %>/fileStream.do?filename="+filename
							});
				imgTags.push(imgTag);
			});
			resultArea.html(imgTags);
		});
		
		$("[name='filename']").trigger("change");
	});
</script>
<style type='text/css'>
	h4{
		border : 1px solid black;
	}
</style>
</head>
<body>
<!-- 어디에서 쿠키를 저장하고, 어디에서 저장된 쿠키를 복원할 것인가??? -->
<h4>current time(on server) : <%=new Date() %> </h4>
<form action='<%=request.getContextPath() %>/fileStream.do'>
<select name='filename' multiple size="10">
<%
	String contentsPath = getServletContext().getInitParameter("contentsPath");
	if(contentsPath==null || contentsPath.isEmpty()) throw new NullPointerException("contentsPath 파라미터 누락");
	File folder = new File(contentsPath);
	
	String[] childArray = folder.list((dir, name)->{
		String mime = application.getMimeType(name);
		boolean result = (mime!=null && mime.startsWith("image/"));
		return result;
	});
	
	for(String child : childArray) {
		boolean match = false;
		if(savedArray!=null){
			Arrays.sort(savedArray);
			match = Arrays.binarySearch(savedArray, child)>=0;
		}
		String selected = match?"selected":"";
		%>
		<option value='<%=child%>' <%=selected %>><%=child%></option>
		<%
	}
%>
</select>
<input type='submit' value='submit'/>
</form>
<div id="resultArea">

</div>

</body>
</html>




















