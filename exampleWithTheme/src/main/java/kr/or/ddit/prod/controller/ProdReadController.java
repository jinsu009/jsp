package kr.or.ddit.prod.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

@CommandHandler
public class ProdReadController {
	IProdService service = new ProdServiceImpl();
	@URIMapping("/prod/prodList.do")
	public String list(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pageParam = request.getParameter("page");
 		String prod_lgu = request.getParameter("prod_lgu");
 		String prod_buyer = request.getParameter("prod_buyer");
 		ProdVO detailSearch = new ProdVO();
 		detailSearch.setProd_buyer(prod_buyer);
 		detailSearch.setProd_lgu(prod_lgu);
 		
 		int currentPage = 1;
 		if(StringUtils.isNumeric(pageParam)) {
 			currentPage = Integer.parseInt(pageParam);
 		}
 		PagingVO<ProdVO> pagingVO = new PagingVO<>(7, 5);
 		pagingVO.setDetailSearch(detailSearch); // 상세 검색 조건
 		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ProdVO> prodList = service.readProdList(pagingVO);
		pagingVO.setDataList(prodList);
		
		if(request.getHeader("Accept").contains("json")) {
			response.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = response.getWriter();	
			){
				mapper.writeValue(out, pagingVO);
			}
			return null;
		}else {
			request.setAttribute("pagingVO", pagingVO);
			// logical view name
			return "prod/prodList";
		}
	}
	
	@URIMapping("/prod/prodView.do")
	public String view(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String prod_id = request.getParameter("what");
		if(StringUtils.isBlank(prod_id)) {
			response.sendError(400, "필수 파라미터 누락");
			return null;
		}
		ProdVO prod = service.readProd(prod_id);
		request.setAttribute("prod", prod);
		return "prod/prodView";
	}
}






