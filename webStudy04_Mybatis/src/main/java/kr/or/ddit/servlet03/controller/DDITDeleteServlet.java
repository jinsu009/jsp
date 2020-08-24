package kr.or.ddit.servlet03.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.servlet03.service.DDITStudentServiceImpl;
import kr.or.ddit.servlet03.service.IDDITStudentService;

//@WebServlet("/ddit/studentDelete.do")
@CommandHandler
public class DDITDeleteServlet {
	private static final long serialVersionUID = 1L;

	IDDITStudentService service = DDITStudentServiceImpl.getInstance();
		
	@URIMapping(value="/ddit/studentDelete.do",method=HttpMethod.POST)
	public String dditDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/**
		 * 1. 요청 분석 (주소, 파라미터, 헤더)
		 * 2. 검증 
		 * 3. 로직
		 * 4. 컨텐츠 공유(scope)
		 * 5. view 선택 > 이동 
		 */
		
		String code = request.getParameter("code");
		
		if(StringUtils.isBlank(code)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"필수 파라미터 누락");
			return null;
		}
				
		ServiceResult result = service.removeStudent(code);
		
		String goPage = null;
		
		if(ServiceResult.OK.equals(result)) {
			goPage = "redirect:/ddit/dditStudents.do";
		}else {
			goPage = "redirect:/ddit/studentView.do?code="+code;
		}
		
		return goPage;
	}

}
