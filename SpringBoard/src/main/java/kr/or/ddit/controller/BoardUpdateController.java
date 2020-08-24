package kr.or.ddit.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board/{bo_no}")
public class BoardUpdateController {
	// 게시글 수정 
	// 유의 : 첨부파일 설정 : 첨부파일 삭제 혹은 첨부파일 추가 
	// 게시글 일반데이타 수정 
	
	@Inject
	IBoardService service;
	
	@ModelAttribute("currentAction")
	public String currentAction(@PathVariable(required = true) int bo_no) {
		return "/board/"+bo_no;
	}
	
	@ModelAttribute("methodType")
	public String methodName() {
		return "put";
	}
	
	// 게시글 내용 보여주기 
	@GetMapping("form")
	public String doGet(@PathVariable(required = true) int bo_no, Model model) {
		// 세션에 담긴 게시글이 아니라면 
		// model 안에 "board"가 포함되어있는지 여부 판단 
		// insert랑 구분하려고 
		if(!model.containsAttribute("board")) { 
			BoardVO board = service.readBoard(bo_no);
			model.addAttribute("board",board);
		}
		return "board/boardForm";
	}
	
	// 게시글 내용 업데이트 
	@PutMapping
	public String doPut(
		@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board,
		BindingResult errors, Model model,
		RedirectAttributes redirectAttributes
	) {
		String goPage = null;
		String msg = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyBoard(board);
			switch(result) {
			case FAIL:
				msg="조금 뒤에 다시 시도하세요";
				redirectAttributes.addFlashAttribute("board", board);
				goPage="redirect:/board/{bo_no}/form";
				break;
			case INVALIDPASSWORD:
				msg ="비밀번호가 일치하지 않아요";
				redirectAttributes.addFlashAttribute("board", board);
				goPage="redirect:/board/{bo_no}/form";
				break;
			default: 
				goPage="redirect:/board/{bo_no}";
				break;
			}
		}else {
			goPage =  "redirect:/board/{bo_no}/form";
			redirectAttributes.addFlashAttribute("board", board);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult",errors);
		}
		// 실패됐을 경우 error 메세지를 정보와 같이 들고 다녀야 한다. 
		
		return goPage;
		
		
	}
	
	
}
