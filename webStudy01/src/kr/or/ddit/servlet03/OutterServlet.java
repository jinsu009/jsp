package kr.or.ddit.servlet03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * inner.jsp를 대신해서 요청을 받을 목적 으로 생성 
 * 
 *
 */
@WebServlet("/outter.do")
public class OutterServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getHeader(name);
//		req.getParameter(name);
		
		req.getRequestDispatcher("/WEB-INF/views/inner.jsp").forward(req, resp);
	}
	// 모델 1 구조와 모델 2(flowcontrol) 구조 의 가장 큰 차이점 resp와 req가 합쳐져 있으면 모델 1, 분리시켜준게 모델 2
	

}
