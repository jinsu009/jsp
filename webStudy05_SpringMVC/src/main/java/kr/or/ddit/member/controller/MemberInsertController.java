package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member/insertMember.do")
public class MemberInsertController{

	@Inject
	IMemberService service;
	private static Logger log = LoggerFactory.getLogger(MemberInsertController.class);
	
	@GetMapping
	public String doGet() {
		return "member/memberForm";
	}
	
	@PostMapping
	public String doPost(
			@Valid @ModelAttribute("newMember") MemberVO memvo,
			Errors errors,
			Model model
			){
		
		log.info("parmeter:      {}",memvo);
		
		String goPage = null;
		String msg = null;
		
		if(!errors.hasErrors()) {
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
		model.addAttribute("msg", msg);
		return goPage;
	}
}
