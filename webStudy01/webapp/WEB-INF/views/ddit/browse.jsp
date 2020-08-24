<%@page import="kr.or.ddit.servlet04.FileWrapper"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<meta charset="UTF-8">
		<title>ddit/browse.jsp/0514</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/browsing.css"/>
		<%
			FileWrapper[] childArr = (FileWrapper[])request.getAttribute("childArr");
		%>
		<div id="MenuBtn" style="margin:10px;"></div>
		<ul id="browser">
			<li id="/"><span class="caret"><%=request.getContextPath() %></span></li>
		</ul>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/browsing.js?time="<%=new Date().getTime() %>></script>
	<script type="text/javascript">
		$("#browser").browse({
			browsiongURL : "<%=request.getContextPath()%>/ddit/contextBrowse.do"
			, eleClass:"caret"
			, processURL : "<%=request.getContextPath()%>/ddit/fileProcess.do"
		});
	</script>
