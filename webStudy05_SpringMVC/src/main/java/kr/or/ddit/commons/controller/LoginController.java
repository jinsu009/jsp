package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.commons.service.AuthenticateServiceImpl;
import kr.or.ddit.commons.service.IAuthenticateService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
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
// 2020-06-11
@Controller
public class LoginController {
	
	@Inject
	WebApplicationContext container;
	ServletContext application;
	
	@PostConstruct
	public void init() {
		// life cycle callback
		application = container.getServletContext();
	}
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	IAuthenticateService service;
   
	@PostMapping(value = "/login/login.do")
	public String doPost(
			HttpSession session,
			Model model,
			@RequestParam(required = false) String mem_id ,
			@RequestParam(required = false) String mem_pw,
			@RequestParam(required = false) String idSave,
			HttpServletResponse response
			) throws IOException{
		
		String goPage = null;
		String msg = null;
		
	
		MemberVO member = new MemberVO(mem_id, mem_pw);
		ServiceResult result = service.authenticated(member);
		// 통과
		if (ServiceResult.OK.equals(result)) {	// 인증 성공
			goPage = "redirect:/";
			// 클라이언트가 단독으로 가지고 있는 session을 사용 , 클라이언트 식별
			session.setAttribute("authUser", member);
			//---------------------------------------
			Cookie idCookie = CookieUtils.createCookie("idCookie",mem_id, 
					application.getContextPath(), TextType.PATH);
			
			int maxAge = 0;
			if("saveId".equals(idSave)) {
				maxAge = 60*60*24*7;
			}else {
			}
			idCookie.setMaxAge(maxAge);
			response.addCookie(idCookie);
			//---------------------------------------
			
		} else {	// 인증 실패
			goPage = "redirect:/login/loginForm.jsp";
			msg = "존재하지 않는 회원 이거나 혹은 비밀번호 오류, 인증실패";
		}
	
		// 데이터 공유하기 위한 설정 		
		session.setAttribute("message", msg);
		// session scope에 attribute 저장 
		
		return goPage;
	}
}
