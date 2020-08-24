<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<ul id="sideNav" class="side-nav-menu side-nav-menu-top-level mb-0">
    <!-- Title -->
    <li class="sidebar-heading h6">DDIT Class 202</li>
    <!-- End Title -->

	<c:if test="${empty authUser }">
	    <!-- Authentication -->
	    <li class="side-nav-menu-item side-nav-has-menu">
	        <a class="side-nav-menu-link media align-items-center" href="#"
	           data-target="#subPages">
	      <span class="side-nav-menu-icon d-flex mr-3">
	        <i class="gd-lock"></i>
	      </span>
	            <span class="side-nav-fadeout-on-closed media-body">Authentication</span>
	            <span class="side-nav-control-icon d-flex">
	        <i class="gd-angle-right side-nav-fadeout-on-closed"></i>
	      </span>
	            <span class="side-nav__indicator side-nav-fadeout-on-closed"></span>
	        </a>
	
	        <!-- Pages: subPages -->
	        <ul id="subPages" class="side-nav-menu side-nav-menu-second-level mb-0">
	            <li class="side-nav-menu-item">
	                <a class="side-nav-menu-link" href="${pageContext.request.contextPath }/login/loginForm.jsp">Login</a>
	            </li>
	            <li class="side-nav-menu-item">
	                <a class="side-nav-menu-link byAjax" href="<c:url value='/member/insertMember.do'/>">Register</a>
	            </li>
	        </ul>
	        <!-- End Pages: subPages -->
	    </li>
	    <!-- End Authentication -->
    </c:if>

    <li class="side-nav-menu-item">
        <a class="side-nav-menu-link media align-items-center byAjax" href="<c:url value='/member/memberList.do'/>">
      <span class="side-nav-menu-icon d-flex mr-3">
        <i class="gd-user"></i>
      </span>
            <span class="side-nav-fadeout-on-closed media-body">All Users</span>
        </a>
    </li>
    
    <!-- Students -->
    <li class="side-nav-menu-item side-nav-has-menu">
        <a class="side-nav-menu-link media align-items-center" href="#"
           data-target="#subStudents">
          <span class="side-nav-menu-icon d-flex mr-3">
            <i class="gd-notepad"></i>
          </span>
            <span class="side-nav-fadeout-on-closed media-body">Students</span>
            <span class="side-nav-control-icon d-flex">
        <i class="gd-angle-right side-nav-fadeout-on-closed"></i>
      </span>
            <span class="side-nav__indicator side-nav-fadeout-on-closed"></span>
        </a>

        <ul id="subStudents" class="side-nav-menu side-nav-menu-second-level mb-0">
            <li class="side-nav-menu-item">
                <a class="side-nav-menu-link byAjax" href="<c:url value='/ddit/dditStudents.do'/>">All Students</a>
            </li>
            <li class="side-nav-menu-item">
                <a class="side-nav-menu-link byAjax" href="<c:url value='/ddit/regist.do'/>">Add new</a>
            </li>
        </ul>
    </li>
    <!-- End Students -->
    
	<!-- Products -->
    <li class="side-nav-menu-item side-nav-has-menu">
        <a class="side-nav-menu-link media align-items-center" href="#"
           data-target="#subProducts">
          <span class="side-nav-menu-icon d-flex mr-3">
            <i class="gd-package"></i>
          </span>
            <span class="side-nav-fadeout-on-closed media-body">Products</span>
            <span class="side-nav-control-icon d-flex">
        <i class="gd-angle-right side-nav-fadeout-on-closed"></i>
      </span>
            <span class="side-nav__indicator side-nav-fadeout-on-closed"></span>
        </a>

        <ul id="subProducts" class="side-nav-menu side-nav-menu-second-level mb-0">
            <li class="side-nav-menu-item">
                <a class="side-nav-menu-link byAjax" href="<c:url value='/prod/prodList.do'/>">All Products</a>
            </li>
             <li class="side-nav-menu-item">
                <a class="side-nav-menu-link byAjax" href="#">Add Product</a>
            </li>
        </ul>
    </li>
    <li class="side-nav-menu-item side-nav-has-menu">
        <a class="side-nav-menu-link media align-items-center" href="#"
           data-target="#subBuyers">
          <span class="side-nav-menu-icon d-flex mr-3">
            <i class="gd-folder"></i>
          </span>
            <span class="side-nav-fadeout-on-closed media-body">Buyers</span>
            <span class="side-nav-control-icon d-flex">
        <i class="gd-angle-right side-nav-fadeout-on-closed"></i>
      </span>
            <span class="side-nav__indicator side-nav-fadeout-on-closed"></span>
        </a>

        <ul id="subBuyers" class="side-nav-menu side-nav-menu-second-level mb-0">
            <li class="side-nav-menu-item">
                <a class="side-nav-menu-link byAjax" href="<c:url value='/buyer/buyerList.do'/>">All Buyers</a>
            </li>
             <li class="side-nav-menu-item">
                <a class="side-nav-menu-link byAjax" href="<c:url value='/buyer/buyerInsert.do'/>">Add Buyer</a>
            </li>
        </ul>
    </li>
    <li class="divider"><div class="border-top ml-2 mr-2"></div></li>
    <li class="sidebar-heading h6">Etc</li>
    <!-- End Products -->
	    <li class="side-nav-menu-item side-nav-has-menu">
	        <a class="side-nav-menu-link media align-items-center" href="#"
	           data-target="#subEtc">
	          <span class="side-nav-menu-icon d-flex mr-3">
	            <i class="gd-view-list-alt"></i>
	          </span>
	            <span class="side-nav-fadeout-on-closed media-body">And So On</span>
	            <span class="side-nav-control-icon d-flex">
	        <i class="gd-angle-right side-nav-fadeout-on-closed"></i>
	      </span>
	            <span class="side-nav__indicator side-nav-fadeout-on-closed"></span>
	        </a>
	
	        <ul id="subEtc" class="side-nav-menu side-nav-menu-second-level mb-0">
	          <li class="side-nav-menu-item">
	            <a class="side-nav-menu-link byAjax" href="<%=request.getContextPath() %>/01/standard.jsp">StandardJSP</a>
	          </li>
	          <li class="side-nav-menu-item">
	            <a class="side-nav-menu-link byAjax" href="<%=request.getContextPath() %>/01/calForm.jsp">Calculator</a>
	          </li>
	          <li class="side-nav-menu-item">
	            <a class="side-nav-menu-link byAjax" href="<%=request.getContextPath() %>/02/calendar.jsp">Calendar</a>
	          </li>
	          <li class="side-nav-menu-item">
	            <a class="side-nav-menu-link byAjax" href="<%=request.getContextPath() %>/05/sessionTimer.jsp">Session Timer</a>
	          </li>
	          <li class="side-nav-menu-item">
	            <a class="side-nav-menu-link byAjax" href="<%=request.getContextPath() %>/ddit/contextBrowse.do">context browsing</a>
	          </li>
	        </ul>
	    </li>
	    <li class="divider"><div class="border-top ml-2 mr-2"></div></li>
</ul>