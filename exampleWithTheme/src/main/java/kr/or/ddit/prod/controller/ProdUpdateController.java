package kr.or.ddit.prod.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class ProdUpdateController {
	IProdService service = new ProdServiceImpl();

	@URIMapping("/prod/prodUpdate.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String prod_id = request.getParameter("what");
		if(StringUtils.isBlank(prod_id)) {
			response.sendError(400, "필수 파라미터 누락");
			return null;
		}
		ProdVO prod = service.readProd(prod_id);
		request.setAttribute("prod", prod);
		request.setAttribute("currentAction", "/prod/prodUpdate.do");
		return "prod/prodForm";
	}

	@URIMapping(value = "/prod/prodUpdate.do", method = HttpMethod.POST)
	public String update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return null;
		}
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(prod, errors);
		String goPage = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case FAIL:
				message = "쫌따 다시 해보셈.";
				goPage = "prod/prodForm";
				break;
			default: // OK
				goPage = "redirect:/prod/prodView.do?what=" + prod.getProd_id();
				req.getSession().setAttribute("lastUpdateProd", prod);
				break;
			}
		} else {
			goPage = "prod/prodForm";
		}

		req.setAttribute("message", message);

		return goPage;
	}

	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(prod.getProd_id())) {
			valid = false;
			errors.put("prod_id", "상품코드 누락");
		}
		if (StringUtils.isBlank(prod.getProd_name())) {
			valid = false;
			errors.put("prod_name", "상품명 누락");
		}
		if (StringUtils.isBlank(prod.getProd_lgu())) {
			valid = false;
			errors.put("prod_lgu", "분류코드 누락");
		}
		if (StringUtils.isBlank(prod.getProd_buyer())) {
			valid = false;
			errors.put("prod_buyer", "거래처코드 누락");
		}
		if (prod.getProd_cost()==null) {
			valid = false;
			errors.put("prod_cost", "구매가 누락");
		}
		if (prod.getProd_price()==null) {
			valid = false;
			errors.put("prod_price", "판매가 누락");
		}
		if (prod.getProd_sale()==null) {
			valid = false;
			errors.put("prod_sale", "세일가 누락");
		}
		if (StringUtils.isBlank(prod.getProd_outline())) {
			valid = false;
			errors.put("prod_outline", "정보 누락");
		}
		if (StringUtils.isBlank(prod.getProd_img())) {
			valid = false;
			errors.put("prod_img", "상품이미지 누락");
		}
		if (prod.getProd_totalstock()==null) {
			valid = false;
			errors.put("prod_totalstock", "재고 누락");
		}
		if (prod.getProd_properstock()==null) {
			valid = false;
			errors.put("prod_properstock", "적정재고 누락");
		}
		return valid;
	}
}
