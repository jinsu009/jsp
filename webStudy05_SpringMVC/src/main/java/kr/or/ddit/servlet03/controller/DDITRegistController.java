package kr.or.ddit.servlet03.controller;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.servlet03.dao.IGradeLicenseDAO;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.DDITStudentVO;

@Controller
@RequestMapping("/ddit/regist.do")
public class DDITRegistController {
	private static final long serialVersionUID = 1L;

	@Inject
	WebApplicationContext container;
	
	ServletContext application;
	
	@PostConstruct
	public void init(){
		application = container.getServletContext();
	}
	
	@Inject
	IDDITStudentService service;

	@Inject
	IGradeLicenseDAO gradedao;
	
	private void attributeSetting(Model model){
		model.addAttribute("gradeList", gradedao.selectGradeList());
		model.addAttribute("licenseList", gradedao.selectLicenseList());
//		model.addAttribute("currentAction","/ddit/regist.do");
	}
	

	@GetMapping
	public String doGet(
		Model model
			){
		attributeSetting(model);
		String goPage = null;
		goPage = "ddit/registForm";
		return goPage;
	}

	// has a 관계 , 의존관계 : servlet에서 dao객체 생성 후 가져다 사용
//	IDDITStudentDAO dao = DDITStudentDAO.getInstance();
	
//	@URIMapping(value="/ddit/regist.do",method=HttpMethod.POST)
	@PostMapping
	public String doPost(
			@RequestParam(name="newStudent") DDITStudentVO vo, 
			@Validated(InsertGroup.class) Errors errors,
			Model model,
			RedirectAttributes redirectattributes
			
			) {

		// 선생님
		attributeSetting(model);

//		DDITStudentVO vo = new DDITStudentVO();
		model.addAttribute("newStudent", vo);

		// 코드를 간결화 시키기 위해 lib 나 framework 필요
//		Map<String, String[]> parameterMap = req.getParameterMap();
//
//		try {
//			BeanUtils.populate(vo, parameterMap);
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
//			return null;
//		}

		String goPage = null;
		String message = null;

		if(!errors.hasErrors()) {
			ServiceResult result = service.createStudent(vo);
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
