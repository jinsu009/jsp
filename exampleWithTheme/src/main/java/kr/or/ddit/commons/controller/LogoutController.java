package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;

@CommandHandler
public class LogoutController{
	@URIMapping(value="/login/logout.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session!=null && !session.isNew()) {
//			session.removeAttribute("authUser");
			session.invalidate();
		}
		String goPage = "redirect:/";
		return goPage;
	}
}






