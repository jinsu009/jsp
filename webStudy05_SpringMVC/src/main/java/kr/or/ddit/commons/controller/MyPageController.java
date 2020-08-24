package kr.or.ddit.commons.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;


//2020-06-11
@Controller
public class MyPageController {
   
	@Inject
	IMemberService service;
	
	@GetMapping("/mypage.do")
//	RequestToViewNameTranslator
	public ModelAndView mypage(HttpSession session) {
		
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
		ModelAndView mav = new ModelAndView();
		mav.addObject("memVO", memVO);
		mav.setViewName("member/mypage");
		return mav;
	}
}
