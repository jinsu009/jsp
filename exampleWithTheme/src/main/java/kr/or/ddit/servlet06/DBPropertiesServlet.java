package kr.or.ddit.servlet06;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.servlet06.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.servlet06.dao.IDataBasePropertyDAO;
import kr.or.ddit.servlet06.service.DataBasePropertyServiceImpl;
import kr.or.ddit.servlet06.service.IDataBasePropertyService;
import kr.or.ddit.vo.DataBasePropertyVO;

@WebServlet("/jdbcDesc.do")
public class DBPropertiesServlet extends HttpServlet{
	
	IDataBasePropertyService service = new DataBasePropertyServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> modelMap = new HashMap<>();
		req.setAttribute("modelMap", modelMap);
		
		service.readDataBaseProperties(modelMap);
		
		String goPage = "/WEB-INF/views/jdbcDesc.jsp";
		req.getRequestDispatcher(goPage).forward(req, resp);
	}
}










