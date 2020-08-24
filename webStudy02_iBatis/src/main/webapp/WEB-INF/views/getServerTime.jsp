<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	// JSON(JavaScript Object Notation)
	/*
		class {	String prop = "value"; }
		//json
		{ prop:"value" }
	*/
	
	Date serverTime = (Date)request.getAttribute("serverTime");
%>
<%-- 		{ "time" : "<%=serverTime %>" } --%>

<div><%=serverTime%></div>
