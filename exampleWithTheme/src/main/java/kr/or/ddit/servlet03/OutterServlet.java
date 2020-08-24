package kr.or.ddit.servlet03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * inner.jsp 를 대신해서 요청을 받을 목적.
 *
 */
@WebServlet("/outter.do")
public class OutterServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getHeader(name)
//		req.getParameter(name)
		req.getRequestDispatcher("/WEB-INF/views/inner.jsp").forward(req, resp);
	}
}







