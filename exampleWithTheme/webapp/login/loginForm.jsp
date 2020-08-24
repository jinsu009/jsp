<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Title -->
    <title>Login</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <!-- Favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/grains-master/public/img/favicon.ico">

    <!-- Template -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/grains-master/public/graindashboard/css/graindashboard.css">
    
  </head>

  <body class="">
    <main class="main">

      <div class="content">

			<div class="container-fluid pb-5">

				<div class="row justify-content-md-center">
					<div class="card-wrapper col-12 col-md-4 mt-5">
						<div class="brand text-center mb-3">
							<a href="/"><img src="${pageContext.request.contextPath }/images/big-logo.png"></a>
						</div>
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">Login</h4>
								<form action="${pageContext.request.contextPath}/login/login.do" method="post">
									<div class="form-group">
										<label for="mem_id">User ID</label>
										<input id="mem_id" type="text" class="form-control" name="mem_id" required autofocus value="${cookie.idCookie.value }">
									</div>

									<div class="form-group">
										<label for="mem_pass">Password
										</label>
										<input id="mem_pass" type="password" class="form-control" name="mem_pass" required>
									</div>

									<div class="form-group">
										<div class="form-check position-relative mb-2">
										  <input type="checkbox" class="form-check-input d-none" 
										  	id="remember" name="idSave" value="saveId" ${not empty cookie.idCookie?"checked":"" }>
										  <label class="checkbox checkbox-xxs form-check-label ml-1" for="remember"
												 data-icon="&#xe936">Remember ID</label>
										</div>
									</div>

									<div class="form-group no-margin">
											<button class="btn btn-primary form-control" type="submit">Sign In</button>
									</div>
									<div class="text-center mt-3 small">
										Don't have an account? <a href="register.html">Sign Up</a>
									</div>
								</form>
							</div>
						</div>
						<footer class="footer mt-3">
							<div class="container-fluid">
								<div class="footer-content text-center small">
									<span class="text-muted">&copy; 2019 Graindashboard. All Rights Reserved.</span>
								</div>
							</div>
						</footer>
					</div>
				</div>
			</div>
      </div>
    </main>
	<script src="${pageContext.request.contextPath }/grains-master/public/graindashboard/js/graindashboard.js"></script>
    <script src="${pageContext.request.contextPath }/grains-master/public/graindashboard/js/graindashboard.vendor.js"></script>
    <c:if test="${not empty message }">
	    <script type="text/javascript">
			alert("${message }");
		</script>
    </c:if>
  </body>
</html>

