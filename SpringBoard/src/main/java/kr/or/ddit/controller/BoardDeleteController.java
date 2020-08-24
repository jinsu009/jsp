package kr.or.ddit.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board/{bo_no}")
public class BoardDeleteController {

	// 게시글 삭제 
	// 첨부파일도 같이 삭제 
	// 자식 게시글은 어째? 지울수가 있나  종속되서 못 지우는데, 같이 지울수 있도록 해야하나 
	// 부모가 지워진 상태 라는것을 알아야한다. 
	// casecade  부모 자식이 끊겨져 있는 상태를 알려준다. 
	
	/**
	 * 게시글 삭제 할 경우
	 * 1. 자식 글이 없을 경우 비밀번호 일치 하면 그냥 삭제 
	 * 2. 자식 글이 있을 경우 
	 * 		1) 자식글과 같이 삭제 : 작성자가 다르기떄문에 삭제 될 수 없다. 
	 * 		2) 자식글이 존재 하므로 삭제 할 수 있는 기능을 막아둔다. 
	 *      3) 데이터만 비워두고 자식글의 데이터는 남겨둔다. 
	 * 3. 자식글 판단 유무를 어디서 해야할까  
	 */
	
	@Inject
	IBoardService service;
	
	@DeleteMapping
	public String doDelete(
		@PathVariable(required = true) int bo_no,
		@RequestParam(required = true) String password, 
		RedirectAttributes redirectattributes
	) {
		String goPage = null;
		String msg = null;
		ServiceResult result = service.removeBoard(new BoardVO(bo_no, password));
		// boardvo
		
		switch(result) {
		case INVALIDPASSWORD :
			goPage = "redirect:/board/{bo_no}";
			msg = "비밀번호 오류";
			break;
		case FAIL : 
			goPage = "redirect:/board/{bo_no}";
			msg = "서버 오류";
			break;
		default :
			goPage = "redirect:/board";
			break;
		}
		redirectattributes.addFlashAttribute("msg",msg);
		return goPage;
	}
	
}
