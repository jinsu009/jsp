package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MemberDeleteController{
	IMemberService service = MemberServiceImpl.getInstance();
	
	@URIMapping(value="/member/deleteMember.do", method=HttpMethod.POST)
	public String deleteMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 누구를 탈퇴시킬까?
		String mem_pass = req.getParameter("mem_pass");
		if(StringUtils.isBlank(mem_pass)) {
			resp.sendError(400, "필수파라미터 누락");
			return null;
		}
		HttpSession session = req.getSession();
		MemberVO authUser = (MemberVO) session.getAttribute("authUser");
		String mem_id = authUser.getMem_id();
//		2. 신원 확인
//		3. 탈퇴처리
		ServiceResult result = service.removeMember(new MemberVO(mem_id, mem_pass));
		String goPage = null;
		String message = null;
		switch (result) {
//		5. 실패 : mypage 로 이동
		case INVALIDPASSWORD:
			goPage = "redirect:/mypage.do";
			message = "비번 오류";
			session.setAttribute("message", message);
			break;
		case FAIL:
			goPage = "redirect:/mypage.do";
			message = "서버 오류";
			session.setAttribute("message", message);
			break;

		default: //OK
//		4. 성공 : 로그아웃시키고,  welcome page 로 이동
			goPage = "redirect:/";
			session.invalidate();
			break;
		}
		
		return goPage;
	}
}











