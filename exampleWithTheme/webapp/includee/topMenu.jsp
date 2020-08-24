<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<nav class="navbar sticky-top flex-md-nowrap p-0 flex-nowrap">
    <div class="navbar-brand-wrapper d-flex align-items-center col-auto">
        <!-- Logo For Desktop View -->
        <a class="navbar-brand navbar-brand-desktop noAjax" href="${pageContext.request.contextPath }">
            <img class="side-nav-hide-on-closed" src="${pageContext.request.contextPath }/images/header-logo.png" 
            alt="Class202" style="width: auto; height: 50px;">
            <img class="side-nav-show-on-closed" src="${pageContext.request.contextPath }/images/mini-logo.png" 
            alt="Class202" style="width: auto; height: 50px;">
        </a>
        <!-- End Logo For Desktop View -->
    </div>
	
	
	
    <div class="header-content col px-md-3">
        <div class="d-flex align-items-center">
            <!-- Side Nav Toggle -->
            <a  class="js-side-nav header-invoker d-flex mr-md-2" href="#"
                data-close-invoker="#sidebarClose"
                data-target="#sidebar"
                data-target-wrapper="body">
                <i class="gd-align-left"></i>
            </a>
            <!-- End Side Nav Toggle -->
		    <a class="h5 col side-nav-menu-link media align-items-center byAjax" href="${pageContext.request.contextPath }/member/memberList.do">
		    	<span class="d-flex mr-3">
	        	<i class="gd-user"></i> 
	        	<span class="media-body ml-3">All Users</span>
		    </a>
		    <a class="h5 col side-nav-menu-link media align-items-center byAjax" href="${pageContext.request.contextPath }/ddit/dditStudents.do">
                <span class="d-flex">
	        	<i class="gd-notepad"></i>    
		    	<span class="media-body ml-3">Students</span>
		    </a>
		    <a class="h5 col py-2 d-none d-md-inline-block text-dark text-decoration-none byAjax" href="${pageContext.request.contextPath }/prod/prodList.do">
		    	<span class="d-flex mr-3">
	        	<i class="gd-package"></i> 
		    	<span class="media-body ml-3">Products</span>
		    </a>
		    <a class="h5 col side-nav-menu-link media align-items-center byAjax" href="${pageContext.request.contextPath }/buyer/buyerList.do">
		    	<span class="d-flex mr-3">
	        	<i class="gd-folder"></i> 
		    	<span class="media-body ml-3">Buyers</span>
		    </a>
		    <a class="h5 col side-nav-menu-link media align-items-center byAjax" href="#">
		    	<span class="d-flex mr-3">
	        	<i class="gd-blackboard"></i> 
		    	<span class="media-body ml-3">FreeBoard</span>
		    </a>
		    <a class="h5 col side-nav-menu-link media align-items-center byAjax" href="#">
		    	<span class="d-flex mr-3">
	        	<i class="gd-book"></i> 
		    	<span class="media-body ml-3">GuestBook</span>
		    </a>
            <!-- User Notifications -->
            <div class="dropdown ml-auto">
                <a id="notificationsInvoker" class="header-invoker" href="#" aria-controls="notifications" aria-haspopup="true" aria-expanded="false" data-unfold-event="click" data-unfold-target="#notifications" data-unfold-type="css-animation" data-unfold-duration="300" data-unfold-animation-in="fadeIn" data-unfold-animation-out="fadeOut">
                    <span class="indicator indicator-bordered indicator-top-right indicator-primary rounded-circle"></span>
                    <i class="gd-bell"></i>
                </a>

                <div id="notifications" class="dropdown-menu dropdown-menu-center py-0 mt-4 w-18_75rem w-md-22_5rem unfold-css-animation unfold-hidden" aria-labelledby="notificationsInvoker" style="animation-duration: 300ms;">
                    <div class="card">
                        <div class="card-header d-flex align-items-center border-bottom py-3">
                            <h5 class="mb-0">Notifications</h5>
                            <a class="link small ml-auto" href="#">Clear All</a>
                        </div>

                        <div class="card-body p-0">
                            <div class="list-group list-group-flush">
                                <div class="list-group-item list-group-item-action">
                                    <div class="d-flex align-items-center text-nowrap mb-2">
                                        <i class="gd-info-alt icon-text text-primary mr-2"></i>
                                        <h6 class="font-weight-semi-bold mb-0">New Update</h6>
                                        <span class="list-group-item-date text-muted ml-auto">just now</span>
                                    </div>
                                    <p class="mb-0">
                                        Order <strong>#10000</strong> has been updated.
                                    </p>
                                    <a class="list-group-item-closer text-muted" href="#"><i class="gd-close"></i></a>
                                </div>
                                <div class="list-group-item list-group-item-action">
                                    <div class="d-flex align-items-center text-nowrap mb-2">
                                        <i class="gd-info-alt icon-text text-primary mr-2"></i>
                                        <h6 class="font-weight-semi-bold mb-0">New Update</h6>
                                        <span class="list-group-item-date text-muted ml-auto">just now</span>
                                    </div>
                                    <p class="mb-0">
                                        Order <strong>#10001</strong> has been updated.
                                    </p>
                                    <a class="list-group-item-closer text-muted" href="#"><i class="gd-close"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End User Notifications -->
            <c:if test="${not empty sessionScope.authUser }">
	            <!-- User Avatar -->
	            <div class="dropdown mx-3 dropdown ml-2" id="authUserArea">
	                <a id="profileMenuInvoker" class="header-complex-invoker" href="#" aria-controls="profileMenu" aria-haspopup="true" aria-expanded="false" data-unfold-event="click" data-unfold-target="#profileMenu" data-unfold-type="css-animation" data-unfold-duration="300" data-unfold-animation-in="fadeIn" data-unfold-animation-out="fadeOut">
	                    <span class="mr-md-2 avatar-placeholder">${sessionScope.authUser.mem_id.substring(0,1) }</span>
	                    <span class="d-none d-md-block">${sessionScope.authUser.mem_name }</span>
	                    <i class="gd-angle-down d-none d-md-block ml-2"></i>
	                </a>
	
	                <ul id="profileMenu" class="unfold unfold-user unfold-light unfold-top unfold-centered position-absolute pt-2 pb-1 mt-4 unfold-css-animation unfold-hidden fadeOut" aria-labelledby="profileMenuInvoker" style="animation-duration: 300ms;">
	                    <li class="unfold-item">
	                        <a class="unfold-link d-flex align-items-center text-nowrap byAjax" href="${pageContext.request.contextPath }/mypage.do">
				                <span class="unfold-item-icon mr-3">
				                  <i class="gd-user"></i>
				                </span>
	                            My Profile
	                        </a>
	                    </li>
	                    <li class="unfold-item unfold-item-has-divider">
	                        <form id="logoutForm" action="${pageContext.request.contextPath }/login/logout.do" method="post"></form>
		                    <a id="logoutBtn" class="unfold-link d-flex align-items-center text-nowrap" 
		                    	href="${pageContext.request.contextPath }/login/logout.do"
		                    	onclick="$('#logoutForm').submit(); return false;"
		                    >
			                <span class="unfold-item-icon mr-3">
			                  <i class="gd-power-off"></i>
			                </span>
	                            Sign Out
	                        </a>
	                    </li>
	                </ul>
	            </div>
	            <!-- End User Avatar -->
            </c:if>
        </div>
    </div>
</nav>