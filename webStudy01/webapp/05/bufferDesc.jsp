<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="8kb" autoFlush="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>05/bufferDesc.jsp/0513</title>
	<body>
		<h4 style="color:teal;"> buffer 제어 </h4>
		<pre>
			- buffer : 전송 효율 향상을 위해 서버상에 존재하는 임시 데이터 저장 공간 (8kb)
			- JspWriter 타입의 out 객체를 통해 버퍼의 상태 확인 및 제어 
				현재 버퍼의 크기 : <span style="color:blue"><%=out.getBufferSize() %></span>
				버퍼의 잔여 용량 : <span style="color:blue"><%=out.getRemaining() %></span>
				버퍼가 flush되거나 비정상 clear된 이후에는 버퍼의 내용을 변경하거나 , forward가 불가능 하다. 
			- 메소드 : 
				flush() : 버퍼 방출 
				clear() : 버퍼 지우기, 더 이상 buffer를 사용할 수 없다.  
				clearBuffer() : 버퍼 지우기 , 다음번 새로운 buffer를 채워넣을 수 있다.
			<%
				for(int i=1; i<=100; i++){
					out.println(i+"번째 반복"); // 12byte
					// if(i==94){out.flush();}
					if(i==95){
						// buffer flush가 된 후 exception 처리 
						//throw new RuntimeException("강제 발생 예외 ");
						// buffer가 1kb 일 때 for문이 중단된다. 
						
// 						request.getRequestDispatcher("/05/imlicitObject.jsp").forward(request, response);
						request.getRequestDispatcher("/05/imlicitObject.jsp").include(request, response);
					}
				} // 1200
			%>
		 총 1kb가 넘는 용량 : autoFlush="true" 에서 자동으로 비워준다.
		 			 : autoFlush="false" IOExeption 발생  
		</pre>
	</body>
</html>