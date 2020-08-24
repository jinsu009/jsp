package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;
import kr.or.ddit.filter.wrapper.PartWrapper;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class ProdInsertController {

	IProdService service = new ProdServiceImpl();

	@URIMapping("/prod/prodInsert.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setAttribute("currentPage", "/prod/prodInsert.do");
		return "prod/prodForm";
	}

	@URIMapping(value = "/prod/prodInsert.do", method = HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setAttribute("currentAction", "/prod/prodInsert.do");
		String currentPage = req.getParameter("currentPage");
		ProdVO prodvo = new ProdVO();
		req.setAttribute("prodvo", prodvo);

		try {
			BeanUtils.populate(prodvo, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return null;
		}
		
		// 이미지 파일 저장 --------------------- 
		
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = true;
		
		//-----
		
		if(req instanceof FileUploadRequestWrapper) {
			PartWrapper imageFile = ((FileUploadRequestWrapper) req).getPartWrapper("prod_image");
			if(imageFile != null) {
				String folderPath = req.getServletContext().getRealPath("/prodImages");
				File saveFolder = new File(folderPath);
				if(!saveFolder.exists()) {
					saveFolder.mkdirs();
				}
				// 실제 파일이 업로드가 됐는지 확인 : 원본파일명의 유무 판단
				String filename = imageFile.getOriginalFilename();
				if(!StringUtils.isBlank(filename)) {
					// 파일 선택이 됨 > 파일 저장 
					imageFile.saveFile(saveFolder);
					// 저장명을 꺼내서 DB에 저장 
					prodvo.setProd_img(imageFile.getSavename());
				}
			}
		}
		
		//-----
			
		valid = valid && validate(prodvo,errors);
		String msg = null;
		String goPage = null;
		if (valid) {
			ServiceResult result = service.createProd(prodvo);
			switch (result) {
			case FAIL:
				msg = "조금뒤에 다시 시도";
				goPage = "prod/prodForm";
				break;
			default:
				goPage = "redirect:/prod/prodList.do?page=" + currentPage;
				req.getSession().setAttribute("lastUpdateProd", prodvo);
				break;
			}
		} else {
			goPage = "prod/prodForm";
		}
		req.setAttribute("msg", msg);
		return goPage;
	}
	

	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true;
//		if (StringUtils.isBlank(prod.getProd_id())) {
//		valid = false;
//		errors.put("prod_id", "상품코드 누락");
//	}
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
		if (prod.getProd_cost() == null) {
			valid = false;
			errors.put("prod_cost", "구매가 누락");
		}
		if (prod.getProd_price() == null) {
			valid = false;
			errors.put("prod_price", "판매가 누락");
		}
		if (prod.getProd_sale() == null) {
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
		if (prod.getProd_totalstock() == null) {
			valid = false;
			errors.put("prod_totalstock", "재고 누락");
		}
		if (prod.getProd_properstock() == null) {
			valid = false;
			errors.put("prod_properstock", "적정재고 누락");
		}

		return valid;
	}
}
