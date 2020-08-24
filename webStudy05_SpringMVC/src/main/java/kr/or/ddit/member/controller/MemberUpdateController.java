package kr.or.ddit.member.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
// 2020-06-11
@Controller
public class MemberUpdateController{

	@Resource(name="memberService")
	IMemberService service;

	// 값을 받는 파라미터의 순서를 잘 지켜야 한다 : 검증은 검증대상의 바로 다음에 작성해야 한다. 
	// errors의 종류는 하나가 아니다. 
	@RequestMapping(value = "/member/updateMember.do", method=RequestMethod.POST)
	public String update(
			@Valid @ModelAttribute("memVO") MemberVO memVO,
			Errors errors,
			Model model
			)  {

//		MemberVO memVO = new MemberVO();
//		req.setAttribute("memVO", memVO);
		// 자격증 처럼 한사람이 여러개의 정보를 가지고 있으니까 , 
		// String[]을 타입에 넣어준다. 
//		Map<String, String[]> memberMap = req.getParameterMap();
//		try {
//			// BeanUtils.populate : 
//			// jsp에서 넘어온값을 간단히 java bean 객체에 맞추어 값을 넣어준다.
//			BeanUtils.populate(memVO, memberMap);
//		} catch (IllegalAccessException | InvocationTargetException e ) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
//			return null;
//		}
		
		String goPage = null;
		String msg = null;
		
		if(!errors.hasErrors()) { // 검증이 성공했으면 
			ServiceResult result = service.modifyMember(memVO);
			if (ServiceResult.OK.equals(result)) {
				goPage = "redirect:/mypage.do";
			} else {
				msg = "수정 오류";
				goPage = "member/mypage";
			}
		}else { // 검증실패
			goPage="member/mypage";
		}
		model.addAttribute("msg", msg);
		return goPage;
	}
	
}
