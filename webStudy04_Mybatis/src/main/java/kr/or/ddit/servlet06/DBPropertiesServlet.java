package kr.or.ddit.servlet06;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.servlet06.service.DataBasePropertyServiceImpl;
import kr.or.ddit.servlet06.service.IDataBasePropertyService;

// 2020-05-19
@WebServlet("/jdbcDesc.do")
public class DBPropertiesServlet extends HttpServlet{
	IDataBasePropertyService service = new DataBasePropertyServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> modelMap = new HashMap<>();
		req.setAttribute("modelMap", modelMap);
		service.readeDataBaseProperties(modelMap);
		String goPage = "/WEB-INF/views/jdbcDesc.jsp";
		req.getRequestDispatcher(goPage).forward(req, resp);
	}
}
