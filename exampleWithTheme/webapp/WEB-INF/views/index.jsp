<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<div class="row" id="dashboard">
	<div class="col-md-6 col-s-12 mb-3 mb-md-4">
        <!-- Card -->
        <div class="card h-100">
            <div class="card-header d-flex">
                <h5 class="h6 font-weight-semi-bold text-uppercase mb-0">
                <a class="byAjax" href="<c:url value='/member/memberList.do'/>">
                User State
                </a>
                </h5>
            </div>
            <div class="card-body p-0" data-url="/member/memberList.do">
            	<table class="table">
            		<thead>
            			<tr>
            				<th>이름</th>
            				<th>휴대폰</th>
            				<th>이메일</th>
            				<th>마일리지</th>
            			</tr>
            		</thead>
            		<tbody>
            			<c:forEach items="${memberList }" var="member">
            				<tr>
            					<td>${member.mem_name }</td>
            					<td>${member.mem_hp }</td>
            					<td>${member.mem_mail }</td>
            					<td>${member.mem_mileage }</td>
            				</tr>
            			</c:forEach>
            		</tbody>
            	</table>
            </div>
        </div>
        <!-- End Card -->
    </div>
    <div class="col-md-6 col-s-12 mb-3 mb-md-4">
        <!-- Card -->
        <div class="card h-100">
            <div class="card-header d-flex">
                <h5 class="h6 font-weight-semi-bold text-uppercase mb-0">
                <a class="byAjax" href="<c:url value='/ddit/dditStudents.do'/>">
                Student State
                </a>
                </h5>
            </div>
            <div class="card-body p-0" data-url="/ddit/dditStudents.do">
            	<table class="table">
            		<thead>
            			<tr>
            				<th>이름</th>
            				<th>나이</th>
            				<th>성별</th>
            				<th>학력</th>
            			</tr>
            		</thead>
            		<tbody>
            			<c:forEach items="${studentList }" var="student">
            				<tr>
            					<td>${student.name }</td>
            					<td>${student.age }</td>
            					<td>${student.gen eq 'M'?'남':'여' }</td>
            					<td>${student.grade }</td>
            				</tr>
            			</c:forEach>
            		</tbody>
            	</table>
            </div>    
        </div>
        <!-- End Card -->
    </div>
</div>