<%@page import="java.util.Arrays"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="kr.or.ddit.Constants"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Cookie[] cookies = request.getCookies();
    	String selectedJson = null; 
    	if(cookies!=null){
    		for(Cookie tmp : cookies){
    			if(Constants.SELECTEDFILE.equals(tmp.getName())){
    				selectedJson = URLDecoder.decode(tmp.getValue(),"UTF-8");
    			}
    		}
    	}
    	String[] savedArray = null;
    	if(selectedJson!=null){
    		ObjectMapper mapper = new ObjectMapper();
    		savedArray = mapper.readValue(selectedJson, String[].class);
    	}
    %>
<!DOCTYPE html>
<html>
	<head>
	<script
  		src="https://code.jquery.com/jquery-3.5.1.min.js"
  		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  		crossorigin="anonymous">
	</script>
		<meta charset="UTF-8">
		<title>03/ContentsUIGenerateServlet/20200511</title>
		
		<script type='text/javascript'>
		// body태그보다 scrpt가 위에 있으므로 body가 생성되기 전에 script가 실행되어 버린다. 
		$(function(){
			let resultArea=$("#resultArea");
			//name은 유일성이 없어서 배열로 관리된다. 
			$("select[name='filename']").on("change", function(event){
				//callback
				let filenames = $(this).val();
				// let : 블럭안에서만 사용되도록 변수의 범위가 한정적이된다.
				// attr : 속성의 값을 결정할 때 사용
				
				let imgTags = [];
				$(filenames).each(function(idx, filename){
					let imgTag = $("<img>").attr({
						// get method 방식을 사용하기 때문에 queryString 형태로 파일의 이름을 전송
						src : "<%=request.getContextPath() %>/fileStream.do?filename="+filename
					});
					imgTags.push(imgTag);
					
				});
				resultArea.html(imgTags);
				
				// 과제(0511) : 비디오 선택했을 때 비디오 태그가 생성되고  이미지가 선택됐을때 이미지태그가 선택되도록 
			});
			$("[name='filename']").trigger("change");
		});
		</script>
		<style type='text/css'>
			h4{	border : 1px solid red;	}
		</style>
	</head>
	<body>
	<h4>current time : <%= new Date() %></h4>
	<form action="<%=request.getContextPath() %>/fileStream.do" >
		<div>
			<select name='filename' multiple size="10">
				<%
				// 05.18 : Q 쿠키를 이용해서 브라우저를 종료했다가 다시 켜도 내가 선택했던 이미지 정보가 남아있도록 코드 작성
				// ( 어디에서 쿠키를 저장하고 , 어디에서 저장된 쿠키를 복원할 것인지 유의 )
				
// 					String contentPath = application.getInitParameter("contentPath");
					String contentPath = getServletContext().getInitParameter("contentPath");
					File folder = new File(contentPath); 
					
					if(contentPath==null || contentPath.isEmpty()) {
						throw new NullPointerException("contentPath 파라미터 누락");
					}	

					String[] childArray = folder.list((dir, name)->{
						// 정식 mime이 없을 경우 예) 한글파일 
						// null값이 들어온다. 
						String mime = application.getMimeType(name);
						boolean result = (mime!=null && mime.startsWith("image/") || mime.startsWith("video/"));
// 						out.println(result);
						return result;
					});
					
					for(String child : childArray) {
						boolean match = false;
						if(savedArray!=null){
							Arrays.sort(savedArray);
							match = Arrays.binarySearch(savedArray, child) >= 0;
						}
						String selected =  match? "selected":"";
						%>
							<option value='<%=child %>' <%=selected %>><%=child %></option>
						<%
					}
				%>
			</select>
		</div>
	</form>
		<br>
		<div id="resultArea" style="border: 5px solid red;"></div>
	</body>
</html>