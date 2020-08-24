package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.ProdVO;

//20-06-12
@Controller
@RequestMapping("/prod")
public class ProdInsertController {
	@Inject
	IProdService service;
	
	@GetMapping("form")
	public String doGet(Model model) {
		model.addAttribute("currentPage", "/prod");
		return "prod/prodForm";
	}

	@PostMapping
	public String doPost(@Validated(value = InsertGroup.class) @ModelAttribute("prodvo") ProdVO prodvo,
			BindingResult errors, Model model, @RequestParam(required = false) String currentPage,
			RedirectAttributes redirectattributes, // session에 있는걸 자동으로 비워줌 4.0이상
			HttpSession session) throws IllegalStateException, IOException {
		model.addAttribute("currentAction", "/prod");
		String msg = null;
		String goPage = null;
		if (!errors.hasErrors()) {

			ServiceResult result = service.createProd(prodvo);
			switch (result) {
			case FAIL:
				msg = "조금뒤에 다시 시도";
				goPage = "prod/prodForm";
				break;
			default:
				goPage = "redirect:/prod?page=" + currentPage;
				redirectattributes.addFlashAttribute("lastUpdateProd", prodvo);
				break;
			}
		} else {
			System.out.println("prod insert else");
			goPage = "prod/prodForm";
		}
		System.out.println(goPage);
		model.addAttribute("msg", msg);
		return goPage;
	}

}
