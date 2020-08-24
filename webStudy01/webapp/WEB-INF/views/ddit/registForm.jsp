<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.DDITStudentVO"%>
<%@page import="java.util.Map.Entry"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		request.getAttribute("newStudent");
		DDITStudentVO stdvo = (DDITStudentVO)request.getAttribute("student");
		
		Map<String, String> errors = (Map)request.getAttribute("errors");
		String message = (String)request.getAttribute("message");
		
		// 수정 시 전달 
		if(stdvo == null) stdvo = new DDITStudentVO();
		if(errors == null) errors = new HashMap<>();
		if(StringUtils.isNotBlank(message)){
			out.println(message);
		}
		
	%>
	<form method="post">
		<input type="hidden" name="code" value="<%=stdvo.getCode()%>">
				이름 : <input type="text" id="name" value="<%=stdvo.getName() %>" name="name" />		
					
				생일 : <input type="date" id="birthday" value="<%=stdvo.getBirthday() %>" name="birthday" />
				
				나이 : <input type="number" id="age" value="<%=stdvo.getAge() %>" name="age" />
				
				학력 : <select name="grade">
						<option value>학력</option>
						<!-- value 값이 없을때는 white space가  전송된다.-->
						<%
							List<Map<String, Object>> gradeList = (List) request.getAttribute("gradeList");
							for (Map<String, Object> map : gradeList) {
								if(stdvo !=null){
								String selected = map.get("text").equals(stdvo.getGrade())?"selected":"";
						%>
									<option <%=selected %> value="<%=map.get("code")%>"><%=map.get("text")%></option>
								<%
									}
								}
								%>
					</select>
					
				성별 : 
				<input type="radio" name="gender" id="genF" value="F" /><label for="genF">여자 </label> 
				<input type="radio" name="gender" id="genM" value="M" /><label for="genM">남자</label>
				
				<script type="text/javascript">
				<%
					String fem = "";
					if(stdvo.getGender().equals("여")){
						fem = "F";
					}else{
						fem="M";
					}
				%>
				
				$("input[value='<%=fem%>']").prop("checked", true);
				</script>
				
				<!-- multiple : 여러개 중복 선택 가능  multiple="multiple" or multiple-->
				<!-- attr :  속성데이터를 가져온다. prop : 속성 값의 존재여부 판단 , true/false -->
				자격증 : <select name="lic_codes" multiple>
							<%
								List<String> licenses = stdvo.getLicense();
								List<Map<String, Object>> licenseList = (List) request.getAttribute("licenseList");
								for (Map<String, Object> map : licenseList) {
									String code = (String) map.get("code");
									String name = (String) map.get("text");
									String selected = licenses!=null && licenses.contains(name)?"selected":"";
							%>
					<option value="<%=code%>" <%=selected %>><%=name%></option>
					<%
						}
					%>
						</select>
				
				경력사항 : 
					<textarea rows="5" cols="50" name="career"><%=stdvo.getCareer() %></textarea>
					<button type="submit">등록</button>
					
					<button type="reset">취소</button>
					
					<button type="button">그저버튼</button>
	</form>
</body>
</html>