package kr.or.ddit.commons.controller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.IndexType;

@WebServlet("/index.do")
public class IndexServlet extends HttpServlet {
	// 05.15
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// queryString에서 받아온 파라미터를 받아준다. .. 분석  
		String jspa = request.getParameter("command");
		
		if(StringUtils.isNoneBlank(jspa)) {
			try {
			String jspaddr = IndexType.index(jspa);
			request.setAttribute("jspaddr", jspaddr);
			}catch (Exception e) {
				// 존재하지 않은 서비스를 요청했을때 클라이언트에게 오류에 관한 상태코드 넘겨주기 - 404 : Not Found
				response.sendError(HttpServletResponse.SC_NOT_FOUND,"요청하신 서비스는 없습니다.");
				return;
			}
		}
		
//		String jspaddr = IndexType.index(Objects.toString(jspa, " "));
//		System.out.println(jspaddr);
//		request.setAttribute("jspaddr", jspaddr);
		
		// model2 구조 
		String goPage ="/WEB-INF/views/index.jsp";
		request.getRequestDispatcher(goPage).forward(request,response);
	}
}
