package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.commons.service.AuthenticateServiceImpl;
import kr.or.ddit.commons.service.IAuthenticateService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.CookieUtils.TextType;
import kr.or.ddit.vo.MemberVO;

/**
 * 
  		login/loginProcess.jsp/0512 /0515
  		1. 디코딩 설정
  		2. 파라미터 확보 
  		3. 검증 
  			1) 통과 :
  				a) 인증 : ( id==password ) 
  					a') 성공 : 웰컴 페이지로 전송 (이동방식이 결정되어야 한다.) 
  						> redirect 구조 (command 처리 완료)
  					b') 실패 : loginform으로 이동 
  						> forward 구조 사용 (request안에 파라미터가 남아있다)
  			2) 불통 : bad request 전송
  			
  		꼭 jsp로 작성하지 않아도 된다. 
 *
 */
//2020-05-19
@WebServlet("/login/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IAuthenticateService service = AuthenticateServiceImpl.getInstance();
   
	// form태그로 전송된 아이디를 잡아서 검증해야하기 때문에 post사용
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // session이 존재하지 않으며 null 값이 돌아온다.
		if(session==null || session.isNew()) {
			// 최초의 요청 .. session이 없을 때 
			// session 만료 , 해커
			response.sendError(400,"form이 없습니다. 로그인 불가 ");
			
			return;
		}
		// 디코딩 설정 
		request.setCharacterEncoding("UTF-8");
		// 파라미터 확보
		String id = request.getParameterValues("mem_id")[0];
		String pw = request.getParameterValues("mem_pw")[0];
		System.out.println(id + " ,,, " + pw);
		String check = request.getParameter("idSave");
//		Cookie idcookie = null;
		String goPage = null;
		boolean redirect = false;
		String msg = null;
		
		// 검증			
		if (StringUtils.isBlank(id) || StringUtils.isBlank(pw)) {	// 검증하지 못했을 때
//	 		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//	 		return;
			goPage = "/login/loginForm.jsp";
			redirect = true;
			msg = "누락 데이터 발생";
		}
		else {
			MemberVO member = new MemberVO(id, pw);
			ServiceResult result = service.authenticated(member);
			// 통과
			if (ServiceResult.OK.equals(result)) {	// 인증 성공
				goPage = "/";
				redirect = true;	// 클라이언트가 단독으로 가지고 있는 session을 사용 , 클라이언트 식별
				session.setAttribute("authUser", member);
				//---------------------------------------
				Cookie idCookie = CookieUtils.createCookie("idCookie",id, 
						request.getContextPath(), TextType.PATH);
				
				int maxAge = 0;
				if("saveId".equals(check)) {
					maxAge = 60*60*24*7;
				}else {
				}
				idCookie.setMaxAge(maxAge);
				response.addCookie(idCookie);
				//---------------------------------------
				
			} else {	// 인증 실패
				goPage = "/login/loginForm.jsp";
				redirect = true;
				msg = "존재하지 않는 회원 이거나 혹은 비밀번호 오류, 인증실패";
			}
		}
		// 데이터 공유하기 위한 설정 		
		session.setAttribute("message", msg);
		// session scope에 attribute 저장 
		
		if(redirect){
			response.sendRedirect(request.getContextPath() + goPage);
		}else{
			request.getRequestDispatcher(goPage).forward(request, response);
		}
	}
}
