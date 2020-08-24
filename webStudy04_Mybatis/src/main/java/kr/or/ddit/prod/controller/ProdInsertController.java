package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
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
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.groups.InsertGroup;
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
		
		
		
		//-----
		if(req instanceof FileUploadRequestWrapper) {
			PartWrapper imageFile = ((FileUploadRequestWrapper) req).getPartWrapper("prod_image");
			prodvo.setProd_image(imageFile);
		}
		
		//-----
		CommonValidator<ProdVO> validator = new CommonValidator<>();
		Map<String, List<String>> errors = validator.validate(prodvo, InsertGroup.class);
		req.setAttribute("errors", errors);
		//-----
			
		String msg = null;
		String goPage = null;
		if (errors.size()==0){
			PartWrapper prod_image = prodvo.getProd_image();
			if(prod_image != null) {
				String folderPath = req.getServletContext().getRealPath("/prodImages");
				File saveFolder = new File(folderPath);
				if(!saveFolder.exists()) {
					saveFolder.mkdirs();
				}
				// 파일 선택이 됨 > 파일 저장 
				prod_image.saveFile(saveFolder);
				// 저장명을 꺼내서 DB에 저장 
				prodvo.setProd_img(prod_image.getSavename());
			}
			
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

}
