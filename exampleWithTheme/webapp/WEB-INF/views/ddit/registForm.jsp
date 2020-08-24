<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<c:if test="${not empty message }">
	<h4>${message }</h4>
</c:if>
<!-- 이름(name), 생년월일(birthday), 나이(age), 학력(grade), 성별(gen), 자격증(license), 경력사항(career) -->
<form method="post" action="<c:url value='${currentAction }'/>">
	<input type="hidden" name="code" value="${student.code }" >
  <div class="form-group row">
    <label for="name" class="col-sm-2 col-form-label">이름</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="name" name="name" value="${student.name }">
      <span class="error">${errors.name }</span>
    </div>
  </div>
  <div class="form-group row">
    <label for="birthday" class="col-sm-2 col-form-label">생년월일</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="birthday" name="birthday"  value="${student.birthday }">
      <span class="error">${errors.birthday }</span>
    </div>
  </div>
  <div class="form-group row">
    <label for="age" class="col-sm-2 col-form-label">나이</label>
    <div class="col-sm-10">
      <input type="number" class="form-control" id="age" name="age"  value="${student.age }">
      <span class="error">${errors.age }</span>
    </div>
  </div>
  <div class="form-group row">
    <label for="age" class="col-sm-2 col-form-label">학력</label>
    <div class="col-sm-10">
      <select name="grade" class="form-control">
			<option value>학력</option>
			<c:forEach items="${gradeList }" var="map">
				<c:set var="selected" value="${(map.CODE eq student.grade) or (map.TEXT eq student.grade)? 'selected' : ''}" />
				<option value="${map.CODE }" ${selected }>${map.TEXT }</option>
			</c:forEach>
		 </select>
		 <span class="error">${errors.grade }</span>
    </div>
  </div>
 <div class="form-group row">
	<div class="form-check form-check-inline">
	  <input class="form-check-input" type="radio" name="gen" id="genF" value="F">
	  <label class="form-check-label" for="genF">여자</label>
	</div>
	<div class="form-check form-check-inline">
	  <input class="form-check-input" type="radio" name="gen" id="genM" value="M">
	  <label class="form-check-label" for="genM">남자</label>
	</div>
	<span class="error">${errors.gen }</span>
	<script type="text/javascript">
		$("input[value='${student.gen }']").prop("checked", true);
	</script>
  </div>
  <div class="form-group row">
    <label for="age" class="col-sm-2 col-form-label">자격증</label>
    <div class="col-sm-10">
		<select class="form-control" name="lic_codes" multiple>
			<c:forEach items="${licenseList }" var="map">
				<c:if test="${not empty student.license }">
					<c:set var="selected" value="${student.license.contains(map.TEXT) ? 'selected':'' }" />
				</c:if>
				<c:if test="${not empty student.lic_codes }">
					${Arrays.sort(student.lic_codes) }
					<c:set var="selected" value="${Arrays.binarySearch(student.lic_codes, map.CODE) ge 0 ? 'selected':'' }" />
				</c:if>
				<option value="${map.CODE }" ${selected }>${map.TEXT }</option>
			</c:forEach>
		</select>
		<span class="error">${errors.lic_codes }</span>
	</div>
  </div>	
  <div class="form-group row">
    <label for="age" class="col-sm-2 col-form-label">경력사항</label>
    <div class="col-sm-10">
     	<textarea class="form-control" name="career" rows="5" cols="50">${student.career }</textarea>
     	<span class="error">${errors.career }</span>
    </div>
  </div>
  <div class="form-group row">
	<button class="btn btn-success mr-2" type="submit">등록</button>			 
	<button class="btn btn-warning mr-2" type="reset">취소</button>			 
	<a class="btn btn-secondary byAjax" href="<c:url value='/ddit/dditStudents.do'/>">목록으로</a>			 
  </div>
</form>













