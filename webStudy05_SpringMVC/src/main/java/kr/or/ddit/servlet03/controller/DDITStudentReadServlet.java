package kr.or.ddit.servlet03.controller;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;

// 2020-06-12
//@WebServlet("/ddit/studentView.do")
@Controller
public class DDITStudentReadServlet {
	private static final long serialVersionUID = 1L;

	
	
	@Inject
	IDDITStudentService service;
	
	@GetMapping("/ddit/studentView.do")
	public String doGet(
			@RequestParam(name="code") String stuCode,
			Model model
			){
		// 3. 학생한명의 정보를 가지고 온다.
		// 4. jsp로 보내준다.
		
		// 2. 요청 분석 
//		String stuCode = request.getParameter("code");
		// 검증  > 실패 400
		int status = 200;
		String msg = "";
		String goPage = null;
		if(StringUtils.isBlank(stuCode)) {
			status = 400;
		}else {
			
			try {
				DDITStudentVO dvo = service.readStudent(stuCode);
				model.addAttribute("selectStu", dvo);
				goPage = "ddit/studentView";
			}catch(DataNotFoundException e){
				status = 400; 
				msg = e.getMessage();
			}
		}
		if(status==200) {
			return goPage;
		}else {
			return null;
		}
	}
}
