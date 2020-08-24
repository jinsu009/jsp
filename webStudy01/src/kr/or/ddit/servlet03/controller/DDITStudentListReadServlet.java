package kr.or.ddit.servlet03.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.servlet03.service.DDITStudentServiceImpl;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;


@WebServlet("/ddit/dditStudents.do")
public class DDITStudentListReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IDDITStudentService service = DDITStudentServiceImpl.getInstance();
       
	// 요청을 받는 역할 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// jsp로 이동하기 전에 dao에 있는 데이터를 확인 하고 넘어가야한다. 
		
		List<DDITStudentVO> allStudents = service.readStudentList();
		request.setAttribute("allStudents", allStudents);
		request.getRequestDispatcher("/WEB-INF/views/ddit/dditStudents.jsp").forward(request, response);
	}
}
