package kr.or.ddit.commons.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;


@WebServlet("/mypage.do")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	IMemberService service = MemberServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//-------
//		if(session==null || session.isNew()) {
//			response.sendError(400);
//			return;
//		}
//		request.setCharacterEncoding("UTF-8");
//		MemberVO memvo = (MemberVO)session.getAttribute("authUser");
//		String goPage = "/member/memberView.do?who="+memvo.getMem_id();
//		request.setAttribute("mem_id", memvo.getMem_id());
//		request.getRequestDispatcher(goPage).forward(request, response);
		
		//---- 선생님
		MemberVO authUser = (MemberVO)session.getAttribute("authUser");
		String mem_id = authUser.getMem_id();
		MemberVO memVO = service.readMember(mem_id);
		request.setAttribute("memVO", memVO);
//		String goPage1 = "/WEB-INF/views/member/memberView.jsp";
		String goPage1 = "/WEB-INF/views/member/mypage.jsp";
		request.getRequestDispatcher(goPage1).forward(request, response);
		
	}
}
