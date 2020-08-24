package kr.or.ddit.prod.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

// 20-06-12
@Controller
@RequestMapping("/prod")
public class ProdReadController {
	private static Logger logger = LoggerFactory.getLogger(ProdReadController.class);

	@Inject
	IProdService service;

	@GetMapping(produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagingVO<ProdVO> ajaxList(
			@RequestParam(name="page",required = false , defaultValue = "1") int currentPage,
			ProdVO detailSearch,
			Model model){
		
		list(currentPage, detailSearch, model);
		PagingVO<ProdVO> pagingVO =  (PagingVO<ProdVO>) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	@GetMapping
	public String list(
			@RequestParam(name="page",required = false , defaultValue = "1") int currentPage,
			ProdVO detailSearch,
			Model model
			) {

		PagingVO<ProdVO> pagingVO = new PagingVO<>(5, 3); // 한페이지당 5개 , 페이지 수 3개 (3개이상이면 next 버튼 활성화)
		pagingVO.setDetailSearch(detailSearch); // 상세 검색 조건 
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ProdVO> prodlist = service.readProdList(pagingVO);
		pagingVO.setDataList(prodlist);
		
		model.addAttribute("pagingVO", pagingVO);
		return "prod/prodList";
	}
	
	@GetMapping("{what}") // pk값을 받기위해 변경시킨다. .. 경로변수  
	public String view(
			@PathVariable(name="what", required = true) String prod_id,
			Model model
			) {
		ProdVO prodvo = service.readProd(prod_id);
		model.addAttribute("prodvo", prodvo);
		return "prod/prodView";
	}
}
