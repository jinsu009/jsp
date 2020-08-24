package kr.or.ddit.servlet03.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.servlet03.service.IDDITStudentService;

//@WebServlet("/ddit/studentDelete.do")
@Controller
public class DDITDeleteServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	IDDITStudentService service;
		
//	@URIMapping(value="/ddit/studentDelete.do",method=HttpMethod.POST)
	@PostMapping(value = "/ddit/studentDelete.do")
	public String dditDelete(
			@Valid @RequestParam(name="code") String code
			){
	
		/**
		 * 1. 요청 분석 (주소, 파라미터, 헤더)
		 * 2. 검증 
		 * 3. 로직
		 * 4. 컨텐츠 공유(scope)
		 * 5. view 선택 > 이동 
		 */
		
//		String code = request.getParameter("code");
		
//		if(StringUtils.isBlank(code)) {
//			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"필수 파라미터 누락");
//			return null;
//		}
				
		ServiceResult result = service.removeStudent(code);
		
		String goPage = null;
		
		if(ServiceResult.OK.equals(result)) {
			goPage = "redirect:/ddit/dditStudents.do";
		}else {
			goPage = "redirect:/ddit/studentView.do?code="+code;
		}
		
		return goPage;
	}

}
