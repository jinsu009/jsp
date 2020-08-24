<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/collectionEL.jsp/0528</title>
</head>
<body>
<h3 style="color:blue">EL collection 사용</h3>
	<pre>
	집합 객체 배열, list, set, map 들을 생성하고 사용해보자  
		<%
			String[] array = new String[]{"cat","puppy"};
			pageContext.setAttribute("array", array);
			
			List list = Arrays.asList("listCat","ListDog");
			pageContext.setAttribute("list", list);
			
			Map<String, Object> map = new HashMap<>();
			map.put("ke1","처음 값");
			map.put("key2","두번째 값");
			map.put("key-3","세번째 값");
			pageContext.setAttribute("map", map);
			
			Set<String> set = new HashSet<>();
			set.add("set1");
			set.add("set2");
			set.add("set3");
			pageContext.setAttribute("set", set);
			
		%>
<%-- 	표현식 : <%=array[14] %> -- 예외처리 발생 --%>
	EL : ${array[14] } -- 예외 없이 공백만 출력 
	---
<%-- 	${list.get(12) }  --예외처리 발생 --%>
	-- 메소드 호출할때 : size를 알고자 할 때 
	${list[13] } -- 출력 목적 : 주로 많이 사용된다. 
	
	${empty map? "empty":map }
	${map.get("key2") }
	${map.key2 }
	${map.get("key-3") }
	${map.key-3 }
	
=========
	${map["key-3"] }
	${map["key-4"] } -- 공백이 나온다.
	
	${set }
	 
	</pre>

</body>
</html>