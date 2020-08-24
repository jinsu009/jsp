<%@page import="java.util.Map.Entry"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>02/SampleForm.jsp/20200508</title>
	</head>
	<body>
		<h4>입학원서를 양식으로 작성하자</h4>
		<pre>
			이름, 생년월일, 나이, 학력, 성별, 작격증, 경력사항 을 학생에게 입력받고 
			form 태그의 action을 이용해서 sampleProcess.jsp에 전송한다. 
		</pre>
		<hr>
		<form action="<%=request.getContextPath() %>/02/sampleProcess.jsp" method="post">
		<%
			Map<String, String> gradeMap = new LinkedHashMap<>();
			gradeMap.put("G001","초대졸");
			gradeMap.put("G002","대졸");
			gradeMap.put("G003","대재");
			gradeMap.put("G004","고졸");
			gradeMap.put("G005","중졸");
			
			Map<String, String> licenseMap = new LinkedHashMap<>();
			licenseMap.put("L001","정보처리기사");
			licenseMap.put("L002","정보보안기사");
			licenseMap.put("L003","SQLD");
			licenseMap.put("L004","SQLP");
		%>
		
			<pre>
				이름 : <input type="text" value="" name="name"/>		
					
				생일 : <input type="date" value="" name="birthday"/>
				
				나이 : <input type="number" name="age"/>
				
				학력 : <select name="grade">
						<option value>학력</option>
						<!-- value 값이 없을때는 white space가  전송된다.-->
						<%
							for(Entry<String, String> entry:gradeMap.entrySet()){
								%>
									<option value="<%=entry.getKey() %>"><%=entry.getValue() %></option>
								<%
							}
						%>
					</select>
					
				성별 : <label><input type="radio" name="gen" value="F" /> 여자 </label> <label><input type="radio" name="gen" value="M" /> 남자</label>
				
				<!-- multiple : 여러개 중복 선택 가능  multiple="multiple" or multiple-->
				<!-- attr :  속성데이터를 가져온다. prop : 속성 값의 존재여부 판단 , true/false -->
				자격증 : <select name="license" multiple>
							<%
								Iterator<String> keys = licenseMap.keySet().iterator();
								while(keys.hasNext()){
									String code = keys.next();
									String name = licenseMap.get(code);
									%>
										<option value="<%=code %>"><%=name %></option>
									<%
								}
							%>
						</select>
				
				경력사항 : 
					<textarea rows="5" cols="50" name="career"></textarea>
				<hr>
					<button type="submit">등록</button>
					
					<button type="reset">취소</button>
					
					<button type="button">그저버튼</button>
			</pre>
		</form>
	</body>
</html>