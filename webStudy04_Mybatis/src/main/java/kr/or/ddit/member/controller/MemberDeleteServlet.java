package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.ModelData;
import kr.or.ddit.mvc.stereotype.RequestParameter;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MemberDeleteServlet  {

	// 누가 탈퇴할 것인지 정보를 가져와야 한다. : session

	// mypage.jsp 에서 탈퇴할 회원의 아이디를 받아온다.
	// 받아온아이디를 넘겨준다.
	// 아이디 받아와서 검증하기 ~
	// 검증한 아이디 맞으면 탈퇴 ~
	// service는 vo를 파라미터로 받는다. *^*
	// member 테이블에서 member_delete 를 Y로 바꿔준다.

	// 일주일 뒤에 삭제 : ? ㅇㅂㅇ ?
	IMemberService service = MemberServiceImpl.getInstance();

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		HttpSession session = req.getSession();
//		MemberVO memVO = (MemberVO) session.getAttribute("authUser");
//		String goPage = null;
//		boolean redirect = false;
//		ServiceResult result = service.removeMember(memVO);
//		if (ServiceResult.OK.equals(result)) {
//			goPage = "/index.do";
//			session.invalidate();
//			redirect = true;
//		} else {
//			goPage = "/mypage.do";
//		}
//		if (redirect) {
//			resp.sendRedirect(req.getContextPath() + goPage);
//		} else {
//			req.getRequestDispatcher(goPage).forward(req, resp);
//		}
//	}

	// 선생님
	@URIMapping(value="/member/deleteMember.do",method=HttpMethod.POST)
	public String delete(
			@RequestParameter(name="mem_pass", required = true) String mem_pass, 
			HttpServletRequest req) {
		// 탈퇴시킬 대상
		HttpSession session = req.getSession();
		MemberVO authUser = (MemberVO) session.getAttribute("authUser");
		String mem_id = authUser.getMem_id();
//		String mem_pass = req.getParameter("mem_pass");
//		if (StringUtils.isBlank(mem_pass)) {
//			resp.sendError(400, "필수 파라미터 누락");
//			return null;
//		}

		// 신원 확인
		// 탈퇴처리
		ServiceResult result = service.removeMember(new MemberVO(mem_id, mem_pass));
		String goPage = null;
		String msg = null;

		switch (result) {
		case INVALIDPASSWORD:
			goPage = "redirect:/mypage.do";
			msg = "비밀번호 오류 ";
			session.setAttribute("msg", msg);

			break;
		case FAIL:
			goPage = "redirect:/mypage.do";
			msg = "서버 오류 ";
			session.setAttribute("msg", msg);

			break;
		default: // OK
			goPage = "redirect:/";
			session.invalidate();
			break;
		}
		
		return goPage;

		// 성공 (로그아웃 시키고 , welcome page : redirect ) or 실패( 마이페이지 이동 : redirection )

	}

}
