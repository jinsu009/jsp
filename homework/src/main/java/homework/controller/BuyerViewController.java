package homework.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework.service.BuyerServiceImpl;
import homework.service.IBuyerService;
import homework.vo.BuyerVO;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;

//@WebServlet("/buyer/buyerView.do")
@CommandHandler
public class BuyerViewController {
	private static final long serialVersionUID = 1L;
       
    IBuyerService service = BuyerServiceImpl.getInstance();
	
    @URIMapping("/buyer/buyerView.do")
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String buyer_id=request.getParameter("where");
		BuyerVO buyVO = service.readBuyer(buyer_id);
		request.setAttribute("buyVO", buyVO);
		String goPage = "/buyer/buyerView";
		return goPage;
//		request.getRequestDispatcher(goPage).forward(request, response);
	}


}
