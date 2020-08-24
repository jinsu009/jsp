package homework.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import homework.dao.ILprodDAO;
import homework.dao.LprodDAOImpl;
import homework.enums.ServiceResult;
import homework.service.BuyerServiceImpl;
import homework.service.IBuyerService;
import homework.vo.BuyerVO;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;

//@WebServlet("/buyer/updateBuyer.do")
@CommandHandler
public class BuyerUpdateController{

	IBuyerService service = BuyerServiceImpl.getInstance();
	ILprodDAO lproddao = LprodDAOImpl.getInstance();
	
	private void attributeSetting(HttpServletRequest req) {
		req.setAttribute("lprodList", lproddao.LprodList());
	}
	
	@URIMapping("/buyer/updateBuyer.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		attributeSetting(req);
		
		String buyerid = req.getParameter("buyer_id");
		
		if(StringUtils.isBlank(buyerid)) {
			resp.sendError(400,"필수 파라미터 누락");
			return null;
		}
		
		BuyerVO buyvo = service.readBuyer(buyerid);
		req.setAttribute("buyer", buyvo);
		
		String goPage = "buyer/buyerForm";
		return goPage;
//		req.getRequestDispatcher(goPage).forward(req, resp);
		
	}
	
	@URIMapping(value="/buyer/updateBuyer.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		attributeSetting(req);
		
		// 1. 수정할 buyer의 정보를 가져오고
		// 1-1. 검증, 
		// 2. vo에 새롭게 set 해준다. 
		
		BuyerVO buyvo = new BuyerVO();
		
		req.setAttribute("buyer", buyvo);

		Map<String, String[]> buyerMap = req.getParameterMap();
		
		try {
			BeanUtils.populate(buyvo, buyerMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return null;
		}
		
		Map<String, String> errors = new LinkedHashMap<>();
		
		boolean valid = vaildate(buyvo, errors);
		req.setAttribute("errors", errors);
		String goPage = "";
		String msg = "";
//		boolean redirect = false;
		
		if(valid) {
			// 검증 성공
			ServiceResult result = service.modifyBuyer(buyvo);
			if(ServiceResult.OK.equals(result)) {
				goPage="redirect:/buyer/buyerList.do"; // 리스트화면
//				redirect = true;
			}else {
				msg="수정오류";
				goPage = "buyer/buyerForm"; // 등록화면
			}
		}else {
			goPage = "buyer/buyerForm"; // 등록화면
		}
		req.setAttribute("msg", msg);
		
		return goPage;
		
	}
	// 검증 메소드
	private boolean vaildate(BuyerVO buyvo, Map<String, String> errors) {
		boolean valid = true;
		
		return valid;
	}
}
