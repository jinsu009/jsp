<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>02/samplePorcess.jsp/20200508</title>
		</head>
	<body>
		<pre> 
			전송 파라미터 처리자  ,, 최종적으로 클라이언트가 보낸 값을 테이블에 전송
			얼마나 많은 데이터가 전송될지 모른다는 걸 유의 
		</pre>
<%
	Map<String, String> nameMap = new LinkedHashMap<>();
	nameMap.put("name", "이름");
	nameMap.put("birthday", "생일");
	nameMap.put("age", "나이");
	nameMap.put("grade", "학력");
	nameMap.put("gen", "성별");
	nameMap.put("license", "자격증");
	nameMap.put("career", "경력사항");
%>
		<table>
			<thead>
				<tr>
					<th>파라미터 명</th>
					<th>파라미터 값</th>
				</tr>
			</thead>
			<tbody>
				<%
					// 데이터를 가져오기 전에 언어를 설정해줘야한다. 
					request.setCharacterEncoding("UTF-8");
				
					Map<String, String[]> sampleMap = request.getParameterMap();				
					
					for(Entry<String, String[]> entry : sampleMap.entrySet()){
						String paramName = entry.getKey();
						String[] values = entry.getValue();
						%>
							<tr>
								<td><%=nameMap.get(paramName) %></td>
								<td><%=Arrays.toString(values) %></td>
							</tr>
						<%
					}
					String value = request.getParameterValues("name")[0];
					out.print(String.format("getParameterValues >> %s , ",value));
					
					String map = request.getParameterMap().get("name")[0];
					out.println(String.format("getParameterMap을 string에 담아서 출력 >> %s , ",map));
					
					Map<String, String[]> exMap =  request.getParameterMap();
					out.println(String.format("getParameterMap을 map에 담아서 출력 >> %s , ", exMap.get("name")[0]));
					
					String names = request.getParameterNames().nextElement();
					out.println(String.format("getParameterNames >> %s",names));
				%>
			</tbody>
		</table>
	</body>
</html>