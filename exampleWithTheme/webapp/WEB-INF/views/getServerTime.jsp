<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Date serverTime = (Date) request.getAttribute("serverTime");
%>    
<div><%=serverTime %></div>
