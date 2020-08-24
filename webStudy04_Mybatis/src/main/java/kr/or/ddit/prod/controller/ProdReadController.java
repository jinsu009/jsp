package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

@CommandHandler
public class ProdReadController {
	private static Logger logger = LoggerFactory.getLogger(ProdReadController.class);

	IProdService service = new ProdServiceImpl();

	@URIMapping("/prod/prodList.do")
	public String list(HttpServletRequest request, HttpServletResponse response) throws Exception{

		String pageParam = request.getParameter("page");
		String prod_lgu = request.getParameter("prod_lgu");
		String prod_buyer = request.getParameter("prod_buyer");
		ProdVO detailSearch = new ProdVO();
		detailSearch.setProd_buyer(prod_buyer);
		detailSearch.setProd_lgu(prod_lgu);

		logger.info("전달된 파라미터 : currentPage : {}", pageParam);

		int currentPage = 1;
		if (StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<ProdVO> pagingVO = new PagingVO<>(5, 3); // 한페이지당 5개 , 페이지 수 3개 (3개이상이면 next 버튼 활성화)
		pagingVO.setDetailSearch(detailSearch); // 상세 검색 조건 
		
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ProdVO> prodlist = service.readProdList(pagingVO);
		pagingVO.setDataList(prodlist);
		
		if(request.getHeader("Accept").contains("json")) {
			// 마샬링 , 직렬화 
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
			return "prod/prodList";
		}
	}
	
	
	// 아이디는 쿼리 스트링으로 넘기기 
	// 상품하나 조회시 해당 상품을 구매한적이 잇는 회원으 목록 을 같이 조회 , 중복 제거 
	@URIMapping("/prod/prodView.do")
	public String view(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String prod_id = req.getParameter("what");
		if(StringUtils.isBlank(prod_id)) {
			resp.sendError(400,"필수 파라미터 누락");
			return null;
		}
		ProdVO prodvo = service.readProd(prod_id);
		req.setAttribute("prodvo", prodvo);
		return "prod/prodView";
	}
}
