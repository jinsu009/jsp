<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<style type='text/css'>
			table{ border-collapse:collapse; }
			td,th{ border : 1px solid pink; }
		</style>
	</head>
	<body>
		<h4>Current Server Time : <%= new Date() %></h4>
	</body>
</html>