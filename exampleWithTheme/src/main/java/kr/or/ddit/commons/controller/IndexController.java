package kr.or.ddit.commons.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceType;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.student.service.DDITStudentServiceImpl;
import kr.or.ddit.student.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

@CommandHandler
public class IndexController {
	IMemberService service1 = MemberServiceImpl.getInstance();
	IDDITStudentService service2 = DDITStudentServiceImpl.getInstance();
	
	@URIMapping("/")
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("command");
		if(StringUtils.isNotBlank(param)) {
			try {
				String contentUrl = ServiceType.findContentUrl(param);
				request.setAttribute("includePage", contentUrl);
			}catch (Exception e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "그런 서비스 없음.");
				return null;
			}
		}else {
			PagingVO<DDITStudentVO> pagingVO = new PagingVO<>(5, 3);
			pagingVO.setCurrentPage(1);
			request.setAttribute("memberList", service1.readMemberList(pagingVO));
			request.setAttribute("studentList", service2.readStudentList(pagingVO));
			request.setAttribute("includePage", "/WEB-INF/views/index.jsp");
		}
		String goPage = "themeTemplate";
		return goPage;
	}


}
