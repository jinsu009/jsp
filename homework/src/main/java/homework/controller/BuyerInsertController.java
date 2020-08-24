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
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import homework.dao.LprodDAOImpl;
import homework.enums.ServiceResult;
import homework.service.BuyerServiceImpl;
import homework.service.IBuyerService;
import homework.vo.BuyerVO;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;

//@WebServlet("/buyer/insertBuyer.do")
@CommandHandler
public class BuyerInsertController {
	private static final long serialVersionUID = 1L;

	IBuyerService service = BuyerServiceImpl.getInstance();
	LprodDAOImpl lproddao = LprodDAOImpl.getInstance();
	
	private void attributeSetting(HttpServletRequest req) {
		req.setAttribute("lprodList", lproddao.LprodList());
	}

	@URIMapping("/buyer/insertBuyer.do")
	public String doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		attributeSetting(request);
		// 가입(등록) 양식으로 연결
		String goPage = "buyer/buyerForm";
		return goPage;
//		request.getRequestDispatcher(goPage).forward(request, response);

	}

	
	@URIMapping(value="/buyer/insertBuyer.do",method=HttpMethod.POST)
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		attributeSetting(request);
		BuyerVO buyvo = new BuyerVO();
		request.setAttribute("newBuyer", buyvo);

		try {
			BeanUtils.populate(buyvo, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return null;
		}

		Map<String, String> errors = new LinkedHashMap<>();

		boolean valid = vaildate(buyvo, errors);
		request.setAttribute("errors", errors);

		String goPage = null;
//		boolean redirect = false;
		String msg = null;

		if (valid) { //검증성공
			ServiceResult result = service.createBuyer(buyvo);
			if (ServiceResult.OK.equals(result)) {
				goPage = "redirect:/buyer/buyerList.do"; // 리스트화면
//				redirect = true;
				HttpSession session = request.getSession();
				session.setAttribute("lastBuyer", buyvo);
			} else {
				msg = "가입실패";
				goPage = "buyer/buyerForm"; // 등록화면
			}
		} else { // 검증
			goPage = "buyer/buyerForm"; // 등록화면
		}
		request.setAttribute("msg", msg);

		return goPage;

	}

	private boolean vaildate(BuyerVO buyervo, Map<String, String> errors) {
		boolean valid = true;

		return valid;
	}

}
