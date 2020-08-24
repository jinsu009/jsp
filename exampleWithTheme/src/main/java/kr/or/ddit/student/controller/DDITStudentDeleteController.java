package kr.or.ddit.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.student.service.DDITStudentServiceImpl;
import kr.or.ddit.student.service.IDDITStudentService;

@CommandHandler
public class DDITStudentDeleteController{
	IDDITStudentService service = DDITStudentServiceImpl.getInstance();
			
	@URIMapping(value="/ddit/studentDelete.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1. 요청 분석(주소, 파라미터, 헤더)
		String code = request.getParameter("code");
//		2. 검증
		if(StringUtils.isBlank(code)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return null;
		}
//		3. 로직
		ServiceResult result = service.removeStudent(code);
		String goPage = null;
		if(ServiceResult.OK.equals(result)) {
			goPage = "redirect:/ddit/dditStudents.do";
		}else {
			goPage = "redirect:/ddit/studentView.do?code="+code;
		}
//		4. 컨텐츠 공유(scope)
		return goPage;
	}
}







