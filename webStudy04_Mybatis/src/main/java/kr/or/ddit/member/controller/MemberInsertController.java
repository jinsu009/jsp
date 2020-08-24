package kr.or.ddit.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.ModelData;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MemberInsertController{

	IMemberService service = MemberServiceImpl.getInstance();
	private static Logger log = LoggerFactory.getLogger(MemberInsertController.class);
	
	@URIMapping("/member/insertMember.do")
	public String doGet() {
		// 가입양식으로 연결시키는 코드가 필요하다. 
//		String goPage = "/WEB-INF/views/member/memberForm.jsp"; // serverside 절대 경로 
//		req.getRequestDispatcher(goPage).forward(req, resp);
		return "member/memberForm";
	}
	
	@URIMapping(value="/member/insertMember.do", method=HttpMethod.POST)
	public String doPost(@ModelData("newMember") MemberVO memvo, HttpServletRequest req){
		// 파라미터 받아서 검증하고 회원가입 시키는 코드 ~~ 
		// pk는 중복을 허용하지 않는다. ~~ 
		// id 중복체크 콘트롤러 필요 ~~~ 
		
		log.info("parmeter:      {}",memvo);
		
		CommonValidator<MemberVO> validator = new CommonValidator<>();
		Map<String, List<String>> errors = validator.validate(memvo);
		req.setAttribute("errors", errors);
		String goPage = null;
		String msg = null;
		
		if(errors.size()==0) {
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
				goPage = "redirect:/index.do";
//				redirect = true;
			}else {
				// 결과 실패
				msg ="가입 실패";
				goPage = "member/memberForm";
				// 입력된 데이터가 전부 누락되지않도록 error메세지와 함께 dispatcher로 전송시킨다. 
			}
			
		}else {
			// 검증실패
			goPage = "member/memberForm";
		}
		req.setAttribute("msg", msg);
		
		return goPage;
		
	}
}
