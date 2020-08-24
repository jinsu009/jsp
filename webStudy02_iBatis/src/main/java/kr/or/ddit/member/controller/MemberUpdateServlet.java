package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/updateMember.do")
public class MemberUpdateServlet extends HttpServlet {

	IMemberService service = MemberServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		MemberVO memVO = new MemberVO();
		
		req.setAttribute("memVO", memVO);
		
		// 자격증 처럼 한사람이 여러개의 정보를 가지고 있으니까 , 
		// String[]을 타입에 넣어준다. 
		Map<String, String[]> memberMap = req.getParameterMap();
		
		try {
			// BeanUtils.populate : 
			// jsp에서 넘어온값을 간단히 java bean 객체에 맞추어 값을 넣어준다.
			BeanUtils.populate(memVO, memberMap);
		} catch (IllegalAccessException | InvocationTargetException e ) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return;
		}
		
		Map<String, String> errors = new LinkedHashMap<>();
		
		boolean valid = vaildate(memVO, errors);
		req.setAttribute("errors", errors);
		String goPage = null;
		String msg = null;
		
		boolean redirect = false;
		
		if(valid) { // 검증이 성공했으면 
			ServiceResult result = service.modifyMember(memVO);
			if (ServiceResult.OK.equals(result)) {
				goPage = "/mypage.do";
				redirect = true;
			} else {
				msg = "수정 오류";
				goPage = "/WEB-INF/views/member/mypage.jsp";
			}
		}else { // 검증실패
//			goPage ="/WEB-INF/views/member/mypage.jsp";
			goPage="";
		}
		req.setAttribute("msg", msg);
		
		if(redirect) {// 성공했을때
			resp.sendRedirect(req.getContextPath()+goPage);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
		
	}
	
	private boolean vaildate(MemberVO memVO, Map<String, String> errors) {
		boolean valid = true;
		
		return valid;
	}
}
