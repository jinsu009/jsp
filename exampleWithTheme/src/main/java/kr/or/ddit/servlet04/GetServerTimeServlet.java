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
 * 서로 다른 언어로 구현된 이기종 시스템 간의 커뮤니케이션.
 * JavaScript 		(XML/JSON)				Java 
 * 1. 메시지 전송 
 *    native data(Java) -> marshalling -> Serialization ~~~~~~~
 * 2. 메시지 수신
 *    ~~~~~~~~ Deserialization -> Unmarshalling -> native data(JavaScript)   
 */
@WebServlet("/getServerTime.do")
public class GetServerTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 분석 (클라이언트가 요구하는 응답 컨텐츠의 형태,  Accept request header)
		// 비동기(ajax->dataType:[html(text/html), json(application/json), 
		//				xml(application/xml), text(text/plain)])
		String accept = req.getHeader("Accept");
		Date serverTime = new Date();
		if(accept.contains("html")) {
			req.setAttribute("serverTime", serverTime);
			String goPage = "/WEB-INF/views/getServerTime.jsp";
			req.getRequestDispatcher(goPage).forward(req, resp);
		}else {
			Object content = serverTime;
			String mime = "text/plain;charset=UTF-8";
			if(accept.contains("xml")) {
				mime = "application/xml;charset=UTF-8";
				// marshalling xml <time>data</time>
				content = String.format("<time>%s</time>", serverTime.toString());
			}else if(accept.contains("json")) {
				mime = "application/json;charset=UTF-8";
				// marshalling json {"time":"시간"}
				content = String.format("{\"time\":\"%s\"}", serverTime.toString());
			}
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









