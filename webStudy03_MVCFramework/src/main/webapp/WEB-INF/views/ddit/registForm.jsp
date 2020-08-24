<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.DDITStudentVO"%>
<%@page import="java.util.Map.Entry"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ddit/registForm.jsp/20200513</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
</head>
<body>
	<h2>
		<%=request.getAttribute("newStudent")%>
		<%=request.getAttribute("errors")%>
		<%=request.getAttribute("message")%>
	</h2>
	<h4 style="color:gold; background-color: pink;">입학원서를 양식으로 작성하자</h4>
	<pre>
			이름, 생년월일, 나이, 학력, 성별, 작격증, 경력사항 을 학생에게 입력받고 
			form 태그의 action을 이용해서 sampleProcess.jsp에 전송한다. 
	</pre>
	<hr>
	<!-- model2에서는  form에서 action이 사라지면 브라우저의 현재 주소로 값을 보내야한다.(servlet)  -->
	<%
		// 신규 등록 및 수정할 때 공통적으로 전달  
// 		request.getAttribute("newStudent");
// 		DDITStudentVO stdvo = (DDITStudentVO)request.getAttribute("student");

// 		Map<String, String> errors = (Map)request.getAttribute("errors");
// 		String message = (String)request.getAttribute("message");
		// 수정 시 전달 
// 		if(stdvo == null) stdvo = new DDITStudentVO();
// 		if(errors == null) errors = new HashMap<>();
// 		if(StringUtils.isNotBlank(message)){
// 			out.println(message);
// 		}
		
	%>
	
	
	<c:if test="${not empty message }">
		alert("${message }");
	</c:if>
	
	<c:set var="stdvo" value="DDITStudentVO.student" />

	<form method="post">
		<table>
			<tr><td colspan="2"><input type="hidden" name="code" value="${student.code}"></td></tr>
			<tr><td>이름 :</td><td><input type="text" id="name" value="${student.name}" name="name" /></td></tr>
			<tr><td>생일 :</td><td><input type="date" id="birthday" value="${student.birthday}" name="birthday" /></td></tr>
			<tr><td>나이 :</td><td><input type="number" id="age" value="${student.age}" name="age" /></td></tr>
			<tr><td>학력 :</td>
			<td><select name="grade">
				<option value>학력</option>
					<!-- value 값이 없을때는 white space가  전송된다.-->
					<c:forEach items="${gradeList }" var="map">
					<option ${not empty student and  map.TEXT eq student.grade?"selected":"" } value="${map.CODE}">${map.TEXT}</option>
					</c:forEach>
					</select>
			</td></tr>
			<tr><td>성별 :</td><td>
			<input type="radio" name="gender" id="genF" value="F" ${ not empty student and student.gender eq '여'? "checked" : ""} />
				<label for="genF">여자 </label> 
				<input type="radio" name="gender" id="genM" value="M"  ${ not empty student and student.gender eq '남'? "checked" : ""} />
				<label for="genM">남자</label>
			</td></tr>
			<tr><td>자격증 :</td>
			<td>
			<select name="lic_codes" multiple>
				<c:forEach items="${licenseList }" var="liMap">
					<option value="${liMap.CODE }" 
					${not empty student.license and student.license.contains(liMap.TEXT) ? "selected":"" }>${liMap.TEXT}</option>
				</c:forEach>
				</select>
			</td></tr>
			<tr>
			<td>경력사항 : </td>
			<td><textarea rows="5" cols="50" name="career">${student.career}</textarea></td>
			</tr>
			<tr>
				<td><button type="submit">등록</button></td>
				<td><button type="reset">취소</button></td>
				<td><button type="button">그저버튼</button></td>
			</tr>
		</table>
				<%--
					List<Map<String, Object>> gradeList = (List) request.getAttribute("gradeList");
					for (Map<String, Object> map : gradeList) {
					if(stdvo !=null){
					String selected = map.get("TEXT").equals(stdvo.getGrade())?"selected":"";
				--%>
				<script type="text/javascript">
				<%--
					String fem = "";
					if("여 ".equals(stdvo.getGender())){
						fem = "F";
					}else{
						fem="M";
					}
				--%>
				$("input[value='<%--=fem--%>']").prop("checked", true);
				</script>
				<!-- multiple : 여러개 중복 선택 가능  multiple="multiple" or multiple-->
				<!-- attr :  속성데이터를 가져온다. prop : 속성 값의 존재여부 판단 , true/false -->
				 <%--
						List<String> licenses = stdvo.getLicense();
						List<Map<String, Object>> licenseList = (List) request.getAttribute("licenseList");
						for (Map<String, Object> map : licenseList) {
							String code = (String) map.get("CODE");
							String name = (String) map.get("TEXT");
							String selected = licenses!=null && licenses.contains(name)?"selected":"";
					--%>
	</form>
</body>
</html>