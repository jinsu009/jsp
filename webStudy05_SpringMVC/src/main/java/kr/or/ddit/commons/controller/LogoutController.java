package kr.or.ddit.commons.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;



//2020-06-11
@Controller
public class LogoutController{
	private static final long serialVersionUID = 1L;
	
	@PostMapping(value = "/login/logout.do")
	public String doPost(HttpSession session, HttpServletRequest request) {

		// session이 null일 수는 없다. 
		if(!session.isNew()) {
			// 정상적인 요청이 들어올 경우
//			session.removeAttribute("authUser");
			session.invalidate(); 
			// session scope안에 모든 정보를 순차적으로 지우고 id를 만료시켜준다. 다시요청을 주면 최초의 요청으로 들어온다.
		}
		
		String goPage="redirect:/";
		return goPage;
	}
}
