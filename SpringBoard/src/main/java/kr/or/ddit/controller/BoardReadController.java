package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

//- command 			: 		handler
//- /board(GET)			:		BoardReadController.list
//- /board/글번호(GET)	: 		BoardReadController.view
//- /board/form(GET)	:		BoardInsertController.form
//- /board(POST)		:		BoardInsertController.insert
//- /board/글번호/form	:		BoardModifyController.form
//- /board/글번호(PUT)	:		BoardModifyController.update
//- /board/글번호(DELETE)	:		BoardModifyController.delete
//
//- /reply/글번호(GET)	:		ReplyController.list
//- /reply/글번호(POST)	: 		ReplyController.insert
//- /reply/글번호(PUT)	:		ReplyController.update
//- /reply/글번호(DELETE)	:		ReplyController.delete

@Controller
@RequestMapping("/board")
public class BoardReadController {

	private static final long serialVersionUID = 1L;
	
	@Inject
	IBoardService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<BoardVO> ajaxList(
			@RequestParam(name="page",required = false, defaultValue = "1") int currentPage,
			BoardVO detailSearch,
			Model model
	){
		list(currentPage, detailSearch, model);
		PagingVO<BoardVO> pagingVO = (PagingVO<BoardVO>) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	@GetMapping
	public String list(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			BoardVO detailSearch,
			Model model
			) {
		PagingVO<BoardVO> pagingVO = new PagingVO<>(10,10);
		pagingVO.setDetailSearch(detailSearch);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<BoardVO> boardList = service.readBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		
		
		model.addAttribute("pagingVO", pagingVO);
		return "board/boardList";	
	}
	
	@GetMapping("{bo_no}")
	public String view(
		@PathVariable(required = true) int bo_no,Model model
	) {
		BoardVO board = service.readBoard(bo_no);
		model.addAttribute("board" , board);
		return "board/boardView";
	}
}
