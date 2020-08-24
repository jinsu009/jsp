package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서로 다른 언어로 구현된 이기종 시스템 간의 커뮤니케이션 
 * JavaScript			(XML/JSON)				Java
 * 1. 메세지 전송 
 * 		native data(Java) > marshalling > Serialization > ~~~
 * 
 * 2. 메세지 수신 
 * 		~~~~ DeSeralization > UnMarshalling > native data (JavaScript) 
 *
 */


@WebServlet("/getServerTime.do")
public class GetServerTimeServlet extends HttpServlet {
	// 05.15 
	// 콘트롤러 ㅣ 요청 받고, 분석, 컨텐츠, viewlayer 생성
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청이 들어오면 요청의 메소드 판단후 doGET OR doPost 결정 
		// super를 지우면 method에 상관없이 데이터 전송
		// super.service(req, resp);
		
		// 요청 분석 ( 클라이언트가 요구하는 응답 컨텐츠의 형태 , Accept request header)
		// 비동기 ( ajax > dataType:[ html(text/html), json(application/json)
		//		, xml(application/xml), text(text/plain) ]
		
		Date serverTime = new Date();
		String accept = req.getHeader("Accept");
		
		// 직렬화가 필요 없으니까 따먼저 빼놓음
		if(accept.contains("html")) {
			// html
			req.setAttribute("serverTime", serverTime); // getServerTime.jsp 로 전달 
			String goPage = "/WEB-INF/views/getServerTime.jsp";
			req.getRequestDispatcher(goPage).forward(req, resp);
		}else{
			
			Object content = serverTime; 
			// if에 걸리지 않으면 datetype의 객체가 응답으로 나가게 된다. 
			
			String mime = "text/plain;charset=UTF-8";
			
			if(accept.contains("xml")) {
				// 마샬링으로 xml 으로 변환 
				// 데이터를 내보내기 위해 직렬화
				
				mime = "application/xml;charset=UTF-8";
				
				//-- marshalling xml : <time>data</time>
				content = String.format("<time>%s</time>", serverTime.toString());
				
				
			}else if(accept.contains("json")) {
				// 마샬링으로 json 으로 변환 
				// 데이터를 내보내기 위해 직렬화
				
				mime = "applicatoin/json;charset=UTF-8";
				
				//-- marshalling json : {"time":"시간" }
				content = String.format("{\"time\":\"%s\"}",serverTime.toString());
			}
			// text
			// plain 이니까 마샬링없이 직렬화만 실행 
			
			// 응답데이터가 json, xml, text구별 
			resp.setContentType(mime);
			
			// 직렬화 
			try(
				PrintWriter out = resp.getWriter();
			){
				out.print(content);
			}
			
				
		}
	}
}
