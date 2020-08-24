<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!-- includee/leftMenu.jsp/0515 -->

 <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="sidebar-sticky pt-3">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" href="<%=request.getContextPath()%>">
              <span data-feather="home"></span>
              Home(Welcome) <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=STANDARDJSP">
              <span data-feather="file"></span>
              standardJSP
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=CALCULATOR">
              <span data-feather="shopping-cart"></span>
         		     계산기
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=CALENDAR">
              <span data-feather="users"></span>
         		     달력
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=SESSIONTIMER">
              <span data-feather="bar-chart-2"></span>
           		   세션타이머
            </a>
         </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=FOLDER">
              <span data-feather="bar-chart-2"></span>
           		   폴더 디렉토리 
            </a>
         </li>
        </ul>
      </div>
      
      <div >
      	<table id="loginMember"  class="table">
      	<thead>
      		<tr><th>접속자 명단</th></tr>
      	</thead>
      	<tbody id="tableBody">
      		<c:set var="userList" value="${applicationScope.userList }"/>
	      		<c:if test="${not empty userList }">
					<c:forEach items="${userList }" var="user">
						<c:choose>
							<c:when test="${user eq authUser }">
							<tr>
								<td class="me" data-email="${user.mem_mail }">${user.mem_name}</td>
							</tr>
							</c:when>
							<c:otherwise>
							<tr>
								<td data-email="${user.mem_mail }">${user.mem_name}</td>
							</tr>
							</c:otherwise>						
						</c:choose>
					</c:forEach>
				</c:if>
				<tr>
					<c:if test="${not empty menuList }">
						<c:forEach items="${menuList }" var="menu">
							<td>
								<a href="${cPath }/${menu.url}">${menu.text}</a>		
							</td>
						</c:forEach>
					</c:if>
				</tr>
			</tbody>
      	</table>
      
      </div>
    </nav>
    
    <script type="text/javascript">
	var userListArea = $("#loginMember").on("click","tr:not(.me)",function(event){
    		let email = $(this).data("email");
    		alert(email)
    	});
    	
	// 06-15 webSocket ------------------------------------------------------------
	const protocol = location.protocol == "http:"?"ws:":"wss:";
	const domain = location.hostname;
	const port = location.port != ""?":"+location.port:"";
	const address = protocol+"//"+domain+port+"${cPath}/pushMessage";
	$("#url").val(address);

	var ws = null;
	ws = new WebSocket(address);
	 //연결에 성공하면 
	ws.onopen = function(event){
		 console.log(event);
	 }
	ws.onclose = function(event){
		 console.log(event);
	 }
	
	ws.onmessage = function(event){
		 let userList = event.data;
		 let tdTags = [];
		
	for(let i=0; i<userList.length; i++){
		let user = userList[i];
		userList = JSON.parse(userList);
		let tr=$("<tr>");
			let tdTag = $("<td>").text(user.mem_name).data("email",user.mem_mail);
			tr.append(tdTag);
			if(user.mem_id == "${authUser.mem_id}"){
				tdTag.addClass("me");
			}
			tdTags.push(tr);
		}
		$("#tableBody").html(tdTags);
	 }
	
	ws.onerror = function(event){
		 console.log(event);
	}
	
	//-------------------------------
//     	setInterval(function(){
//     		$.ajax({
// 				url : "${cPath}/getUserList.do",
// 				dataType : "json",
// 				// Accept : 
// 				// html > text/html, Content-Type:text/html
// 				// json >  application/json 
// 				success : function(resp) {
// 					let tdTags = [];
// 					$.each(resp, function(idx, user){
// 					let tr=$("<tr>");
// 						let tdTag = $("<td>").text(user.mem_name).data("email",user.mem_mail);
// 						tr.append(tdTag);
// 						if(user.mem_id == "${authUser.mem_id}"){
// 							tdTag.addClass("me");
// 						}
// 						tdTags.push(tr);
// 					});
// 					$("#tableBody").html(tdTags);
// 				},
// 				error : function(errorResp) {
// 					console.log(errorResp.status + ":"+ errorResp.responseText);
// 				}
// 			});
//     	},3000);
    	
    </script>