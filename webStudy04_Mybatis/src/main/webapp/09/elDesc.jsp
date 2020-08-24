<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/elDesc.jsp/0528</title>
</head>
<body>

<h3 style="color:blue">ExpressionLanguage(표현언어)</h3>

<pre>
	- 데이터를 표현하기 위해 표현식을 대체하는 스크립트 언어 (제어문을 갖지 않는다.)
	- 사용형태 : \${속성명(setattribute name)}
	- 속성명 !! 
	- 역슬래시 넣어주면 단순 문자로 취급
	
	EL의 기능 
		1. 속성데이터 확보 ★★★★★
		2. EL연산자 지원
			- 연산의 종류 
				1) 산술 : +(concat연산을 수행할수 없다.),-,*,/,% , 데이터 타입 : 실수,
					1+2 = ${1+2 } , 1+"2" = ${1+"2" } , "1"+"2" =${"1"+"2" }
					1*2 = ${1*2 } ,1/2 (실수 연산) = ${1/2 }
				2) 논리 : &&(and), ||(or), !(not)
					true and true = ${true and true }
					not true  = ${not true }
					not true or false = ${not true or false }
				3) 비교 : &gt;(gt) , &lt;(lt) , >=(ge) , &lt;=(le) , ==(eq) , !=(ne)
					${1 lt 2 } , ${1 eq "1" }
				4) 단항 (★★) : empty (속성데이터의 존재여부확인 ) , not empty
					<%
						String test = "     ";
						pageContext.setAttribute("test", test);
						List list = new ArrayList();
						pageContext.setAttribute("list", list);
						list.add("data");
						list.add("data");
						list.add("data");
						list.add("data");
						list.add("data");
					%>
					test : ${empty test } 
					true : 아예 존재하지 않을 때, null, ""(length=0) .
					list : ${empty list }
					list에 값이 없으면 true가 나오고 있으면  false가 나온다. 
				5) 삼항 : 조건식 ? 참표현:거짓표현 
					${not empty list and list.size() gt 3? list:"비어있다" }
		3. (속성으로 공유되고 있는) 자바 객체에 대한 접근
			<%
				MemberVO memvo = new MemberVO("r090","RRR");
				request.setAttribute("member", memvo);
			%> 
			표현식 : <%=memvo.getMem_id() %>
			EL : ${member.mem_id }
			EL2 : ${member.mem_test }
			--memberVO에 getter를 만들어주면 사용가능하다. 
			EL3 : 연산배열 구조  (EL과 같은표현 방법이다.) : ${member['mem_id'] }
			
		4. (속성으로 공유되고 있는) Collection객체에 대한 접근 
			<a href="collectionEL.jsp">collectionEL.jsp 참고</a>
		
		5. EL 객체 지원
			- Scope 객체 : pageScope , requestScope, sessionScope, applicationScope
			- 11개나 있다. ㅇㅂㅇ!!
			<a href="elObject.jsp">elObject.jsp참고</a>
	<%
		pageContext.setAttribute("attrName", "페이지 데이터");
		request.setAttribute("attrName", "요청 데이터");
		session.setAttribute("attrName", "세션 데이터");
		application.setAttribute("attrName", "어플리케이션 데이터");
	%>
	
	<%=pageContext.getAttribute("attrName") %>
	${attrName }  
	${sessionScope.attrName }  
	-- 같은 이름의 scope가 여러개라면 범위가 좁은 scope부터 차례로 불러온다.
	-- 특정한 scope만 가져온다면 객체를 통해서 가져온다.  
	-- el과 jstl은 한쌍으로 다닌다. 
	
</pre>

</body>
</html>