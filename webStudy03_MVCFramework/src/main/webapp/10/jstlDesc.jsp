<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/jstlDesc.jsp/0528</title>
<style type="text/css">
	.yellow{background-color: yellow}
	.lime{background-color: lightgreen;}
</style>
</head>
<body>
<h4 style="color:brown">JSTL(Java Standard Tag Library)</h4>

2~9단 을 테이블로 완성  , 3번째에 background color주기 
<table border="1" id="gugutable">

<%-- 	<c:forEach var="i" begin="2" end="9" step="1"> --%>
	<c:forEach var="i" begin="2" end="9" step="2" varStatus="vs">
		<c:choose>
<%-- 			<c:when test="${i eq 4 }"> --%>
			<c:when test="${vs.count eq 3 }">
				<c:set var ="clzName" value="yellow"/>
			</c:when>
<%-- 			<c:when test="${i eq 9 }"> --%>
			<c:when test="${vs.last }">
				<c:set var="clzName" value="lime"/>
			</c:when>
			<c:otherwise>
				<c:set var = "clzName" value="" />
			</c:otherwise>
		</c:choose>
		<tr class="${clzName }">	
			<c:forEach var="j" begin="1" end="9" >
					<td >
					   ${i} * ${j} = ${i*j }
					</td>	
			</c:forEach>
		</tr>
	</c:forEach>
</table>


<br><br>

<form action="">
	<input type="number" name="age" >
	<input type="submit" value="나이에 맞는 메세지를 달라 ~">
</form>
<div>
<c:set var="age" value="${param.age }" />
<c:set var="one" value="좋은 때 " />
<c:set var="two" value="친구" />
<c:set var="three" value="행님 " />
<c:set var="four" value="건강주의" />
<c:set var="five" value="명퇴주의" />
<c:set var="six" value="어르신" />
<c:if test="${not empty age  }">
	<c:choose>
		<c:when test="${age gt 9 and age lt 19  }">
			${one }
		</c:when>
		<c:when test="${age lt 30  }">
			${two }
		</c:when>
		<c:when test="${age lt 40  }">
			${three }
		</c:when>
		<c:when test="${age lt 50  }">
			${four }
		</c:when>
		<c:when test="${age lt 60  }">
			${five }
		</c:when>
		<c:otherwise>
			${six }
		</c:otherwise>
	</c:choose>
</c:if>
</div>
<div></div>

<pre>
	- 커스텀 태그들 중 많이 사용되는 것들을 모아놓은 라이브러리
	- 커스텀 태그 사용방법
		1. taglib로 사용할 커스텀 태그를 로딩, prefix 결정 
		2. &lt;prefix:tagname&gt;
	
	- JSTL 의 종류 
		1. core 태그 (c)
			1) 변수지원 : 
			set : 속성을 생성하고 값을 할당.
			remove : 속성 제거 주의 ㅣ 제거할 영역을 명시 .
			var : 속성 명
			value : 값, 표현식, EL
			scope : 영역
				<c:set var="attrName" value="<%=new Date() %>" scope="page"/>
				<c:set var="attrName2" value="${pageContext.request.contextPath }" scope="page"/>
				<c:set var="attrName1" value="속성값" scope="application"/>
				default : scope="page"
				page : ${pageScope.attrName }
				application : ${applicationScope.attrName1 }
	
				<c:remove var="attrName" scope="page"/>
				page 삭제 후  : ${pageScope.attrName }
				
			2) 조건문 지원 : 
				if : 단일 조건문,else 구조가 없다. 
				choose - when - otherwise : 다중 조건문
				test : 조건식, EL, 표현식 , 태그의 바디로 블럭구문을 완성함 
				<c:if test="${true }">
					조건을 만족하는 구문 	
				</c:if>
				q1. ${not empty param.test? param.test:"없다" }
				<c:if test="${not empty param.test }">
					${param.test}
				</c:if>
				<c:if test="${empty param.test }">
					"없다"
				</c:if>
				-------------------------------------
				<c:choose>
					<c:when test="${not empty param.test }">
						${param.test}
					</c:when>
					<c:otherwise>
						"없다."
					</c:otherwise>
				</c:choose>
			3) 반복문 지원 :
				foreach :
					var : 블럭 속성 명
					begin : 초기 값
					end : 종료 값 
					step : 증감치 , > 0 , 1 이면 생략가능
					items : 집합객체 - 향상된 for문
					var Status : LoopTagStaus 객체로 반복에 대한 상태 정보를 가진 객체  
				 
					일반 for(int i =3; i<=3, i++) 
						<c:forEach var="i" begin="3" end="3" step="1">
							${i }
						</c:forEach>
				 	향상된 for ( 블럭변수 : 집합객체 )
				 		<c:forEach items='<%=Arrays.asList("value1","value2") %>' var="element">
				 			${element }
				 		</c:forEach>
					
				forTokens : 일정 토큰을 대상으로 반복문 수행 . 
					StringTokenizer .
					"a,b,c".split(",");  : 콤마를 기준으로 잘라서 문자열을 배열로 만든다.
					token : 문장의 의미를 결정하는 최소한의 구성요소 
					delims : 구분자 
					 <c:forTokens items="아버지 가방에 들어가신다." delims=" " var="token">
					 	${token }
					 </c:forTokens>
					 예) 어떤 통신망을 통해서 "1,2,3,4,5" 데이터집합을 받고 세 배로 불려서 출력해야할 때 
					 <c:forTokens items="1,2,3,4,5" delims="," var="intToken">
						 ${intToken *3 }
					 </c:forTokens> 
					 <hr>
					 <c:forTokens items="1,2,3,4,5" delims="," var="intToken" varStatus="vs">
						 ${intToken * 4 } ${not vs.last ? ',' : ' ' }
					 </c:forTokens> 
				
			4) URL 재처리 :
				§ url
					- 클라이언트 절대 경로 자동완성
					url : <c:url value="/member/memberList.do" var="listURL"></c:url>
					결과 : /webStudy02_iBatis/member/memberList.do
<%-- 					<a href="${listURL }">회원목록</a> --%>
					
					- queryString 만들어준다. :  멤버페이지로 이동후 바로 2페이지 보기
<%-- 					 <c:url value="/member/memberList.do" var="listURL2"> --%>
<%-- 					 	<c:param name="page" value="2"/> --%>
<%-- 					 	<c:param name="searchWord" value="대전"/> --%>
<%-- 					 	<c:param name="searchType" value="address"/> --%>
<%-- 					 </c:url> --%>
<%-- 					<a href="${listURL2 }">회원목록2</a> --%>
					
					- 쿠키가 없을경우 session parameter완성 
					- 크롬 : 설정 : 모든 쿠키 차단 : 로그인 상태는 유지 되지 않는다.
					- 이때 세션이 삭제 되지 않도록 하는역할도 있다.
					- 현재 콘텍스트에서 쿠키가 지원이 되지 않으면 jsessionid를 붙여준다. ★★★★★★★★★ - 보안에 취약 ! 
				  
				§ redirect
					- client side주소완성후 redirection
<%-- 				 <c:redirect url="/member/memberList.do"/> --%>
<%-- 				 <a href="${listURL}">회원목록으로 !</a> --%>
				
			5) 기타 : out 
				§ import 
					- 콘텐츠 제한 없이 가져오기 
					<c:import url="https://www.naver.com" var="naver"/>
					<c:out value="${naver }" escapeXml="false"></c:out>
					true : ecape : 해당 사이트의 소스가 가져와진다.
					false : 홈화면이 가져와진다. 
				
				§ out
			
		2. fmt 태그 (fmt)
		3. function 라이브러리 (fn) 
</pre>
</body>
</html>