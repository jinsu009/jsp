package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class ProdUpdateController {
	// /prod/prodUpdate.do 라는 요청으로 수정 작업 , get, post 두가지 처리 방식 필요 
	// get : prod/prodForm.jps ui구성   모든 처리는 동기방식
	
	IProdService service = new ProdServiceImpl();

	@URIMapping(value="/prod/prodUpdate.do", method=HttpMethod.GET)
	public String toGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String prodId = req.getParameter("what");
		
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400,"필수 파라미터 누락");
			return null;
		}
		
		ProdVO pvo = service.readProd(prodId);
		req.setAttribute("prodvo", pvo);
		req.setAttribute("currentAction", "/prod/prodUpdate.do");
		
		return "prod/prodForm";
	}
	
	@URIMapping(value="/prod/prodUpdate.do", method=HttpMethod.POST)
	public String toPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String currentPage = req.getParameter("currentPage");
		ProdVO pvo = new ProdVO();
		req.setAttribute("pvo", pvo);
		
		Map<String,String[]> prodMap = req.getParameterMap();
		
		try {
			BeanUtils.populate(pvo, prodMap);
		}catch(Exception e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return null;
		}
		
		if(req instanceof FileUploadRequestWrapper) {
			PartWrapper imageFile = ((FileUploadRequestWrapper) req).getPartWrapper("prod_image");
			pvo.setProd_image(imageFile);
		} 
		// vo에 이미지 넣고 이미도 포함해서 검증하기 
		
		
		CommonValidator<ProdVO> validator = new CommonValidator<>();
		Map<String, List<String>> errors = validator.validate(pvo, UpdateGroup.class);
		req.setAttribute("errors", errors);
		
		String goPage = null;
		String msg = null;
		
			
		
		if (errors.size()==0) {
			PartWrapper prod_image = pvo.getProd_image();
			
			if(prod_image != null) {
				String folderPath = req.getServletContext().getRealPath("/prodImages");
				File saveFolder = new File(folderPath);
				if(!saveFolder.exists()) {
					saveFolder.mkdirs();
				}
				// 파일 선택이 됨 > 파일 저장 
				prod_image.saveFile(saveFolder);
				// 저장명을 꺼내서 DB에 저장 
				pvo.setProd_img(prod_image.getSavename());
			}
			
			ServiceResult result = service.modifyProd(pvo);
			switch (result) {
			case FAIL:
				msg = "나중에 다시 시도";
				goPage = "prod/prodForm";
				break;
			default: // OK
//				goPage = "redirect:/prod/prodView.do?what=" + prod.getProd_id();
				goPage = "redirect:/prod/prodList.do?page="+currentPage;
				req.getSession().setAttribute("lastUpdateProd", pvo);
				break;
			}
		} else {
			goPage = "prod/prodForm";
		}
		req.setAttribute("msg", msg);
		return goPage;
	}
}
