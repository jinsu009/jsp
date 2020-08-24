package kr.or.ddit.commons.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;


//@WebServlet("/login/logout.do")
@CommandHandler
public class LogoutController{
	private static final long serialVersionUID = 1L;
	
	@URIMapping(value="/login/logout.do",method=HttpMethod.POST)
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 검증 
		HttpSession session = request.getSession();
		
		if(session!=null && !session.isNew()) {
			// 정상적인 요청이 들어올 경우
//			session.removeAttribute("authUser");
			session.invalidate(); 
			// session scope안에 모든 정보를 순차적으로 지우고 id를 만료시켜준다. 다시요청을 주면 최초의 요청으로 들어온다.
		}
		
		String goPage="redirect:/";
		return goPage;
//		response.sendRedirect(request.getContextPath() + goPage);
	}

}
