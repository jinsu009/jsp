package homework.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import homework.service.BuyerServiceImpl;
import homework.service.IBuyerService;
import homework.vo.BuyerVO;
import homework.vo.PagingVO;
import homework.vo.SearchVO;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;


@CommandHandler
public class BuyerListController  {
	private static final long serialVersionUID = 1L;
       
	IBuyerService service = BuyerServiceImpl.getInstance();
   
	@URIMapping("/buyer/buyerList.do")
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageParam = request.getParameter("page");
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");

		SearchVO searchVO = new SearchVO();
		searchVO.setSearchType(searchType);
		searchVO.setSearchWord(searchWord);
		
		int currentPage = 1;
		if (StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<BuyerVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readBuyerCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<BuyerVO> buyerList = service.readBuyerList(pagingVO);

		pagingVO.setDataList(buyerList);

		request.setAttribute("pagingVO", pagingVO);

		String goPage = "/buyer/buyerList";
		return goPage;
	}
}
