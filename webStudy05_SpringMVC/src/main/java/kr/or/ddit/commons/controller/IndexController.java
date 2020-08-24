package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enums.IndexType;

@Controller
public class IndexController {
	// 2020-06-11
	private static final long serialVersionUID = 1L;
	
	@GetMapping("/")
	public String doGet(@RequestParam(required = false) String command, Model model, HttpServletResponse response) throws IOException{
		
		if(StringUtils.isNoneBlank(command)) {
			try {
			String contentUrl = IndexType.index(command);
			model.addAttribute("jspaddr", contentUrl);
			}catch (Exception e) {
				// 존재하지 않은 서비스를 요청했을때 클라이언트에게 오류에 관한 상태코드 넘겨주기 - 404 : Not Found
				response.sendError(HttpServletResponse.SC_NOT_FOUND,"요청하신 서비스는 없습니다.");
				return null;
			}
		}
		
//		String jspaddr = IndexType.index(Objects.toString(jspa, " "));
//		System.out.println(jspaddr);
//		request.setAttribute("jspaddr", jspaddr);
		
		// model2 구조 
		String goPage ="index";
//		request.getRequestDispatcher(goPage).forward(request,response);
		return goPage;
	}
}
