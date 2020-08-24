package kr.or.ddit.servlet03.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.servlet03.dao.IGradeLicenseDAO;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;

@Controller
public class DDITStudentUpdateController{
	private static final long serialVersionUID = 1L;
    
	@Inject
	IDDITStudentService service;
	@Inject
	IGradeLicenseDAO gradedao;
	
	private void attributeSetting(Model model){
		model.addAttribute("gradeList", gradedao.selectGradeList());
		model.addAttribute("licenseList", gradedao.selectLicenseList());
	}
	
	@GetMapping("/ddit/studentUpdate.do")
	public String doGet(
			@Valid @RequestParam(name="code") String code,
			Model model) {
		
		attributeSetting(model);
//		String code = request.getParameter("code");
//		if(StringUtils.isBlank(code)) {
//			response.sendError(400,"필수 파라미터 누락");
//			return null;
//		}
		DDITStudentVO vo = service.readStudent(code);
		model.addAttribute("student", vo);
		return "ddit/registForm";
//		request.getRequestDispatcher("/WEB-INF/views/ddit/registForm.jsp").forward(request, response);
		
	}
	
	@PostMapping(value = "/ddit/studentUpdate.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String doPost(
			@Valid @ModelAttribute(name="newStudent")DDITStudentVO vo ,
			Errors errors,
			Model model,
			RedirectAttributes redirectattributes			
			) {
		// 자격증정보는 기존의 정보를 삭제하고 다시 update
		// 1. jsp에서 가져온 값을 가공하기 위해 dao로 전송 
		
		attributeSetting(model);
		
//		DDITStudentVO vo = new DDITStudentVO();
		
		model.addAttribute("newStudent", vo);
		
//		Map<String, String[]> parameterMap = req.getParameterMap();
//		try {
//			BeanUtils.populate(vo, parameterMap);
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
//			return null;
//		}
				
		String goPage = null;
		String message = null;

		if(!errors.hasErrors()) {
			// 검증 통과
			// 저장
			ServiceResult result = service.modifyStudent(vo);
			if (ServiceResult.OK.equals(result)) {
				// 성공한 정보를 담아서 넘겨준다.
				// vo 또는 vo안에 있는 식별코드
				goPage = "redirect:/ddit/dditStudents.do";
				redirectattributes.addFlashAttribute("lastStudent", vo);

			} else {
				message = "조금 뒤에 다시 해보세요";
				goPage = "ddit/registForm";
			}
		} else {
			// 검증 실패
			// web-inf : dispatch.forward
			goPage = "ddit/registForm";
			// scope를 통해 데이터를 공유
		}

		model.addAttribute("message", message);
		return goPage;
	}

}
