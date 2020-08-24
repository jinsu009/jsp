<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>WEB-INF/index.jsp/0515</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/js/jquery-ui-1.12.1/jquery-ui.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/js/bootstrap-4.5.0-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css"> <!-- 최우선으로 적용하고 싶은 css는 하단에 작성한다. -->
		<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
  
  <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
  
  <!-- java script 를 넣기 위해서는 구조를 알아야하고 한군데에서만 넣어줘도 된다.  -->
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-4.5.0-dist/js/bootstrap.bundle.min.js"></script>
	
	<c:if test="${ not empty msg }">
		alert("${msg}");
		<c:remove var="msg" scope="session"/>
	</c:if>
	
	</head>
	<body>
	<tiles:insertAttribute name="topMenu"/>
		<div class="container-fluid">
		  <div class="row">
		  <tiles:insertAttribute name="leftMenu"/>
		    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
		    <tiles:insertAttribute name="content" />
		    </main>
		  </div>
		</div>
	</body>
</html>