package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MypageController{
	IMemberService service = MemberServiceImpl.getInstance();
	@URIMapping("/mypage.do")
	public String mypage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String mem_id = req.getParameter("who");
		HttpSession session = req.getSession();
		MemberVO authUser = (MemberVO) session.getAttribute("authUser");
		String mem_id = authUser.getMem_id();
		MemberVO member = service.readMember(mem_id);
		req.setAttribute("member", member);
		String goPage = "member/mypage";
		return goPage;
	}
}
