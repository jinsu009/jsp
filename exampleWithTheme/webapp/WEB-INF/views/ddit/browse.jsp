<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/jquery-ui-1.12.1/jquery-ui.min.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>  
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/browsing.css?time=<%=new Date().getTime()%>" >
<ul id="browser">
	<li id="/"><span class="caret">${pageContext.request.contextPath }</span>
</ul>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/browsing.js?time=<%=new Date().getTime()%>"></script>
<script type="text/javascript">
	$("#browser").browse({
		browsingURL:"${pageContext.request.contextPath }/ddit/contextBrowse.do"
		, eleClass:"caret"
		, processURL :"${pageContext.request.contextPath }/ddit/fileProcess.do"
	});
</script>  










