<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>07/cookieDesc.jsp/0518</title>
	</head>
	<body>
		<h3 style="color:brown;">Cookie</h3>
		<pre>
			§ protocol Http의 속성 
				- Connectionless/Stateless : 비연결지향 무상태 특성을 가진다. 
				- 해당 단점을 보완하는 방법 : session, cookie 
				session : (server의 저장공간 사용 / 만료시간이 정해져 있다) / 
				
				1. Cookie : (client의 저장공간 사용 ) / 대화 유지를 위한 상태 정보를 클라이언트 쪽에 저장하는 개념
				
					(쿠키를 사용하는 단계)
					1) 쿠키생성
					2) 응답과 함께 클라이언트로 전송
					3) 브라우저별 쿠키 저장소에 각기 따로 저장.
					4) 다음 요청이 발생할 때 함께 서버로 재전송 
					5) 요청에 포함된 쿠키를 통해 상태 복원
					(쿠키생성)
					<%
						// 1,2
						//Cookie tmpCookie = new Cookie("firstCookie","FirstCookie"); // 데이터 타입 : String
						//response.addCookie(tmpCookie); // add : 한번에 응답데이터에 여러개의 cookie를 보낼수 있다.
						
						// 3,4 > 크롬창에서 확인 
						
						// 5
						String value = null;
						Cookie[] cookies = request.getCookies();
						// 식별자를 통해 firstcookie의 값만 가져온다. 
						for(Cookie tmp : cookies){
							String name = tmp.getName();
							if("firstCookie".equals(name)){ // null point에 안전한 코드 
								value = tmp.getValue();
							}
						}
					%>
					<span style="color:blue">쿠키 : <%=value %></span>
					
					*** 쿠키의 속성 
					1) name : 
					2) value : 문자열만 가능, 특수문자가 포함된다면, URL encoding방식으로 인코딩 필요 
					<span style="color:blue"><%
					
// 						String cookieValue = URLEncoder.encode("한글 국산 쿠키","UTF-8");
// 						Cookie koreanCookie = new Cookie("koreanCookie",cookieValue);
// 						response.addCookie(koreanCookie);
// 						Cookie[] cookies2 = request.getCookies();
						
// 						for(Cookie tmp : cookies2){
// 							String name = tmp.getName();
// 							String value2 = URLDecoder.decode(tmp.getValue(),"UTF-8");
// 							out.println(String.format("%s : %s ", name, value2));
// 						}
					%></span>
					
					3) domain/host : 쿠키를 다음 요청에 재전송 시킬때 포함 여부를 결정  
									 만약, host name을 생략하면, 해당기관의 모든 서버를 대상으로 쿠키 재전송
									 ** 주의 : 쿠키의 출처와 설정된 domain이 다를경우 사용할 수 없다. 
						 (최하위)     (최상위)
						- www.naver.com : 3level구조  / com = company / 
							GTLD(Global Top Level Domain)
						- www.naver.co.kr : 4level구조  / kr = korea
							NTLD(National Top Level Domain)
						- 맨 앞 : host name (단말기)
						- 중간 : 기관의 명칭 
						- 맨 뒤 : pop level domain
						- 하위레벨은 상위레벨에 포함된다. 
						
						<%
						// 톰캣 7.0d으로 테스트 
// 							Cookie allDomainCookie = new Cookie("allDomainCookie", "ALL!!!DOMAIN");
// 							allDomainCookie.setDomain(".lsj.com"); 
// 							// 다음 요청시 네이버가 가지고 있는 모든 host를 가지고 cookie 재전송 
// 							response.addCookie(allDomainCookie);
// 							Cookie[] cookies3 = request.getCookies();
// 							if(cookies3!=null){
// 								for(Cookie tmp : cookies3){
// 									String name = tmp.getName();
// 									String value2 = URLDecoder.decode(tmp.getValue(),"UTF-8");
// 									out.println(String.format("%s : %s ", name, value2));
// 								}
// 							}							

// 							Cookie allpath = new Cookie("allPath","AllPath");
// 							allpath.setPath("/"); // 모든 경로에서 all path가 나오는 것을 확인
// 							allpath.setPath(request.getContextPath()+"/06"); // 타겟을 명확하게 입력하여 다른경로에서만 all path가 나오는것을 확인
// 							response.addCookie(allpath);
							// 쿠키는 name, domain, path 세가지로 식별성을 판별한다. 
							// 위의 세가지 전부가 동일해야 같은 쿠키라고 여긴다. 
							
						%>
					4) path : 생략한 경우, 쿠키가 생성된 경로로 설정된다.
							     다음 요청이 설정된 경로 이하로 발생한 경우, 쿠키가 재전송된다.   
					<a href="./viewCookie.jsp">동일경로에서 쿠키확인</a>
					<a href="../06/viewCookie.jsp">다른경로에서 쿠키확인</a>
					setDomain, setPath : 개발자가 나중에 설계를 할 때 모든 페이지에서 쿠키를 사용할 수 있게끔 설정 (?)
					
					5) maxAge : 생략한 경우 , 기본값으로 세션과 동일한 만료시간이 설정된다. (정해진 세션시간 보다 오래 남기게 하기 위해서 사용)
								초단위로 설정된다. 
								0 > 기존에 저장된 쿠키 삭제 (반드시 maxAge이외의 나머지 설정도 동일해야 한다.)
								-1 > 브라우저 종료시 만료 
					<%
						Cookie longLive = new Cookie("longLive","LongLive~~~~");
// 						longLive.setPath(request.getContextPath()); // 일주일이내에는 어디서든 이 쿠키를 사용(?)
// 						longLive.setMaxAge(60*60*24*7); //최대 일주일 동안 저장되는 cookie
// 						longLive.setMaxAge(-1); // 브라우저 종료시 바로 삭제 
						longLive.setMaxAge(0); // 기존에 저장된 쿠키를 삭제 
						longLive.setSecure(true);
						response.addCookie(longLive);
					%>
		</pre>
	</body>
</html>