<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String path = (String) request.getAttribute("jspaddr");
	// return type가 object 이기 때문에 형변환이 필요하다
	if(StringUtils.isNoneBlank(path)){
		pageContext.include(path);
	}
%>
