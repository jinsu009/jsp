<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%!
    	public String text ="텍스트"; 
    // 전역변수의 목적은 데이터 공유이다. 
    // 하지만 jsp에서는 그대로 사용할 수 없으므로 대안으로 scope라는 개념을 활용
    	public void test(){
    		System.out.println("테스트");
    	}
    %>

<body>
<h4>JSP(Java Server Page) 01/standard.jsp</h4>
<%--=includeeVariable --%>  <!-- actionTag.jsp -->
<pre>
	표준 구성 요소 
	1. 정적 텍스트 : 문자열, HTML, CSS, JavaScript, .. client url 구성시 사용되는 언어 
	2. 스크립트 요소 : 
		- Scriptlet(스크립틀릿) : <%-- Object 변수  = null; test(); //자바코드,실행코드   --%>,
								실행코드는 차후에 컨테이너에 의해 서블릿 코드가 파싱될 때, 지역코드화가된다.(_JSPService)  
		- Expression(표현식) : <%--=변수 --%> , 출력데이터,<%--=test() --%>
		- Directive(지시자) :  <%--@ --%> , 현재 jsp 페이지에 대한 환경정보(부가정보) 설정,
		 (page(required) : 현재 페이지에 대한 전처리 ,
		  taglib(optional) : 커스텀 태그 라이브러리 로딩,
		  include(optional) : 정적 내포, )
		- Declaration(선언부) : <%--! 전역변수, 메소드에 대한 선언 --%>, 관행적으로 상단의 page밑에 작성해준다.  
		- Comment(주석) : <%-- --%>
			* client side comment : HTML(지양하는 것이 좋다.), CSS, JavaScript 
			* server side comment : JAVA, JSP  
			* 보안과 응답데이터 축소를 위해 server side comment를 사용하는게 좋다. 
			<%--
// 				comment
				/* multiline
				comment */
			--%>
			
<!-- 			test -->
	<style type="text/css">
/* 		pre { */
/* 			background-color : yellow; */
/* 		} */
	</style>
	<script type="text/javascript">
// 		var test = null;
	</script>
	3. 기본 객체 : (scope를 알기전 알아야할 개념)
	4. 액션 태그 : 
	5. 표현 언어(EL) :
	6. JSTL : 
</pre>
