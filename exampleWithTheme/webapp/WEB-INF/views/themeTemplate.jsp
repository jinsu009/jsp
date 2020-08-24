<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Title -->
    <title>Class202 | Bootstrap theme example</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <!-- Favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/grains-master/public/img/favicon.ico">


    <!-- Template -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/grains-master/public/graindashboard/css/graindashboard.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/js/bootstrap-4.5.0-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css" >
</head>

<body class="has-sidebar has-fixed-sidebar-and-header">
<!-- Header -->
<header class="header bg-body" id="topHeader">
    <jsp:include page="/includee/topMenu.jsp" />
</header>
<!-- End Header -->

<main class="main">
    <!-- Sidebar Nav -->
    <aside id="sidebar" class="js-custom-scroll side-nav">
        <jsp:include page="/includee/leftMenu.jsp" />
    </aside>
    <!-- End Sidebar Nav -->

    <div class="content">
        <div class="py-4 px-3 px-md-4">
	        <div class="card mb-3 mb-md-4">
				<div class="card-body" id="bodyContent">
					<c:if test="${not empty includePage }">
						<jsp:include page="${includePage }" />
					</c:if>	
					<c:if test="${empty includePage }">
						<img style="width: 80%; height: auto;" src="${pageContext.request.contextPath }/images/java_background.png" />
					</c:if>
				</div>
			</div>			
        </div>

        <!-- Footer -->
        <footer class="small p-3 px-md-4 mt-auto">
            <div class="row justify-content-between">
                <div class="col-lg text-center text-lg-left mb-3 mb-lg-0">
                    <ul class="list-dot list-inline mb-0">
                        <li class="list-dot-item list-dot-item-not list-inline-item mr-lg-2"><a class="link-dark" href="#">FAQ</a></li>
                        <li class="list-dot-item list-inline-item mr-lg-2"><a class="link-dark" href="#">Support</a></li>
                        <li class="list-dot-item list-inline-item mr-lg-2"><a class="link-dark" href="#">Contact us</a></li>
                    </ul>
                </div>

                <div class="col-lg text-center mb-3 mb-lg-0">
                    <ul class="list-inline mb-0">
                        <li class="list-inline-item mx-2"><a class="link-muted" href="#"><i class="gd-twitter-alt"></i></a></li>
                        <li class="list-inline-item mx-2"><a class="link-muted" href="#"><i class="gd-facebook"></i></a></li>
                        <li class="list-inline-item mx-2"><a class="link-muted" href="#"><i class="gd-github"></i></a></li>
                    </ul>
                </div>

                <div class="col-lg text-center text-lg-right">
                    &copy; 2019 Graindashboard. All Rights Reserved.
                </div>
            </div>
        </footer>
        <!-- End Footer -->
    </div>
</main>
<script src="${pageContext.request.contextPath }/grains-master/public/graindashboard/js/graindashboard.js"></script>
<script src="${pageContext.request.contextPath }/grains-master/public/graindashboard/js/graindashboard.vendor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/themeSupport.js?time=${System.currentTimeMillis() }"></script>
</body>
</html>