package kr.or.ddit.servlet03.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.servlet03.service.DDITStudentServiceImpl;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;

// 2020-05-20
@WebServlet("/ddit/studentView.do")
public class DDITStudentReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 1. 의존관계형성 
	IDDITStudentService service = DDITStudentServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 3. 학생한명의 정보를 가지고 온다.
		// 4. jsp로 보내준다.
		
		// 2. 요청 분석 
		request.setCharacterEncoding("UTF-8");
		String stuCode = request.getParameter("code");
		// 검증  > 실패 400
		int status = 200;
		String msg = "";
		String goPage = null;
		if(StringUtils.isBlank(stuCode)) {
			status = 400;
			
		}else {
			
			try {
				DDITStudentVO dvo = service.readStudent(stuCode);
				request.setAttribute("selectStu", dvo);
				goPage = "/WEB-INF/views/ddit/studentView.jsp";
			}catch(DataNotFoundException e){
				status = 400; 
				msg = e.getMessage();
			}
		}
		if(status==200) {
			request.getRequestDispatcher(goPage).forward(request, response);
			
		}else {
			response.sendError(status,msg);
		}
	}
}
