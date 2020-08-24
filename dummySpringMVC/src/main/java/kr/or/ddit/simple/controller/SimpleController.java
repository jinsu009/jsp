package kr.or.ddit.simple.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

import kr.or.ddit.simple.service.SimpleService;
import kr.or.ddit.simple.vo.SimpleVO;

@Controller
@RequestMapping("/simple.do")
public class SimpleController {

	private SimpleService service;
	// 필수전략 > 생성자 

	@Inject
	public SimpleController(SimpleService service) {
		super();
		this.service = service;
	}
	
//	@RequestMapping(value = "/simple.do", method = RequestMethod.GET)
	@GetMapping
	public String form() {
		return "simple/simpleForm";
	}
	
//	@RequestMapping(value="/simple.do", method = RequestMethod.POST)
	@PostMapping
//	public String plus(int leftOp,
//			@RequestParam(required = true) int rightOp
//			, Model model) {
	public String plus(@Valid @ModelAttribute("simple") SimpleVO simple, Errors errors){
//		model.addAttribute("result",result);
		
		if(!errors.hasErrors()) {
			service.plus(simple); // 연산의결과가 돌아옴 
		}
		return "simple/simpleForm";
	}
	
	// 객체에대한 검증 : adapter : @Valid
	
}
