package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.ProdVO;
//20-06-12
@Controller
@RequestMapping("/prod/{what}")
public class ProdUpdateController {
	// /prod/prodUpdate.do 라는 요청으로 수정 작업 , get, post 두가지 처리 방식 필요 
	// get : prod/prodForm.jps ui구성   모든 처리는 동기방식
	
	@Inject
	IProdService service;

	@GetMapping("form")
	public String toGet(
			@PathVariable(name="what") String prod_id,
			Model model 
		) {
		ProdVO prodvo = service.readProd(prod_id);
		model.addAttribute("prodvo", prodvo);
		model.addAttribute("currentAction", "/prod/"+prod_id);
		return "prod/prodForm";
	}
	
	@PutMapping
	public String toPost(
			@Validated(UpdateGroup.class) @ModelAttribute("prodvo") ProdVO prodvo,
			Errors errors,
			@RequestParam(required = false) String currentPage,
			Model model,
			RedirectAttributes redirectattributes
			) throws IOException, ServletException {
		
		model.addAttribute("currentAction", "/prod/"+prodvo.getProd_id());
			
		String goPage = null;
		String msg = null;
		
		if (!errors.hasErrors()) {
					
			ServiceResult result = service.modifyProd(prodvo);
			switch (result) {
			case FAIL:
				msg = "나중에 다시 시도";
				goPage = "prod/prodForm";
				break;
			default: // OK
				goPage = "redirect:/prod?page="+currentPage;
				redirectattributes.addFlashAttribute("lastUpdateProd", prodvo);
				break;
			}
		} else {
			goPage = "prod/prodForm";
		}
		model.addAttribute("msg", msg);
		return goPage;
	}
}
