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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/insertMember.do")
public class MemberInsertServlet extends HttpServlet{

	IMemberService service = MemberServiceImpl.getInstance();
	private static Logger log = LoggerFactory.getLogger(MemberInsertServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 가입양식으로 연결시키는 코드가 필요하다. 
		String goPage = "/WEB-INF/views/member/memberForm.jsp"; // serverside 절대 경로 
		req.getRequestDispatcher(goPage).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 받아서 검증하고 회원가입 시키는 코드 ~~ 
		// pk는 중복을 허용하지 않는다. ~~ 
		// id 중복체크 콘트롤러 필요 ~~~ 
		
		req.setCharacterEncoding("UTF-8");
		
		MemberVO memvo = new MemberVO();
		
		req.setAttribute("newMember", memvo);
		
		try {
			BeanUtils.populate(memvo, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return;
		}
		
		log.info("parmeter:      {}",memvo);
		
		// 어떤 검증에 걸렸는지 확인하기 위해 errors의 map을 사용 
		Map<String, String> errors = new LinkedHashMap<>();
		
		boolean valid = vaildate(memvo, errors);
		req.setAttribute("errors", errors);
		String goPage = null;
		boolean redirect = false;
		String msg = null;
		
		if(valid) {
			// 검증에 통과가 되면
			ServiceResult result = service.createMember(memvo);
			
//			switch(result) {
//			case PKDUPLICATED :
//				goPage="/WEB-INF/views/member/memberForm.jsp";
//				break;
//				
//			case FAIL:
//				goPage ="/WEB-INF/views/member/memberForm.jsp";
//				break;
//			default :
//				goPage = "/";
//				redirect = true;
//				break;
//			}
			
			if(ServiceResult.OK.equals(result)) {
				// 결과가 성공
				goPage = "/index.do";
				redirect = true;
			}else {
				// 결과 실패
				msg ="가입 실패";
				goPage = "/WEB-INF/views/member/memberForm.jsp";
				// 입력된 데이터가 전부 누락되지않도록 error메세지와 함께 dispatcher로 전송시킨다. 
			}
			
		}else {
			// 검증실패
			goPage = "/WEB-INF/views/member/memberForm.jsp";
//			goPage = "";
			
		}
		req.setAttribute("msg", msg);
		
		if(redirect) {
			// 성공
			resp.sendRedirect(req.getContextPath()+goPage);
			// 클라이언트의 데이터를 가지고 다니면서 이동 하는 방법 
			// 클라이언트 측에서 이동하므로 contextpath가 같이 작성되야 한다. 
		}else {
			// 실패
			req.getRequestDispatcher(goPage).forward(req, resp);
			// 서버안에서만 이동하는 방법
		}
		
	}
	
	
	private boolean vaildate(MemberVO memvo, Map<String, String> errors) {
		boolean valid = true;

		if (StringUtils.isBlank(memvo.getMem_id())) {
			valid = false;
			errors.put("memid", "아이디 누락");
		}
		if (StringUtils.isBlank(memvo.getMem_pass())) {
			valid = false;
			errors.put("mempass", "비밀번호 누락");
		}
		if (StringUtils.isBlank(memvo.getMem_name())) {
			valid = false;
			errors.put("memname", "이름 누락");
		}
		if (StringUtils.isBlank(memvo.getMem_regno1())) {
			valid = false;
			errors.put("memregno1", "주민번호 앞자리 누락");
		}
		if (StringUtils.isBlank(memvo.getMem_regno2())) {
			valid = false;
			errors.put("memregno2", "주민번호 뒷자리 누락");
		}
		if (StringUtils.isBlank(memvo.getMem_zip())) {
			valid = false;
			errors.put("memzip", "우편주소 누락");
		}
		if (StringUtils.isBlank(memvo.getMem_add1())) {
			valid = false;
			errors.put("memadd1", "주소 누락");
		}
		if (StringUtils.isBlank(memvo.getMem_add2())) {
			valid = false;
			errors.put("memadd2", "상세주소 누락");
		}
		if (StringUtils.isBlank(memvo.getMem_hometel())) {
			valid = false;
			errors.put("memhometel", "집 전화번호 누락");
		}
		if (StringUtils.isBlank(memvo.getMem_comtel())) {
			valid = false;
			errors.put("memcomtel", "회사 전화번호 누락");
		}
		if (StringUtils.isBlank(memvo.getMem_mail())) {
			valid = false;
			errors.put("memmail", "메일 누락");
		}
		if (StringUtils.isNoneBlank(memvo.getMem_bir())) {
			// 생일데이터가입력이 됐으면..
			try {
				new SimpleDateFormat("yyyy-MM-dd").parse(memvo.getMem_bir());
			} catch (ParseException e) {
				valid = false;
				errors.put("birthday", "생일 형식 확인");
			}
		}
		return valid;
	}
}
