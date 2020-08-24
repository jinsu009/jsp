package kr.or.ddit.commons.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class UserListReadController {

	@URIMapping("/getUserList.do")
	public String userList(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Set<MemberVO> userList = (Set<MemberVO>) req.getServletContext().getAttribute("userList");
		
		resp.setContentType("application/json;charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		
		try(
			PrintWriter out = resp.getWriter();
		){
			mapper.writeValue(out, userList);
		}
		
		return null; 
		// 논리적인 view name일때는 null 반환 
	}
	
}
