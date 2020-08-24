package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

// 2020-05-26

@WebServlet("/member/memberView.do")
public class MemberViewServlet extends HttpServlet{

	IMemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String mem_id = req.getParameter("who");
		MemberVO memVO = service.readMember(mem_id);
		req.setAttribute("memVO", memVO);
		String goPage = "/WEB-INF/views/member/memberView.jsp";
		req.getRequestDispatcher(goPage).forward(req, resp);
		
	}
	
}
