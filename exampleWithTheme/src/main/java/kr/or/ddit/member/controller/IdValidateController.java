package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;

@CommandHandler
public class IdValidateController{
	IMemberService service = MemberServiceImpl.getInstance();
	@URIMapping(value="/member/idValidate.do", method=HttpMethod.POST)
	public String validate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String inputId = req.getParameter("inputId");
		if(StringUtils.isBlank(inputId)) {
			resp.sendError(400, "아이디 누락");
			return null;
		}
		Map<String, Object> resultMap = new HashMap<>();
		String result = null;
		try {
			service.readMember(inputId);
			result = "DUPLICATE";
		}catch (DataNotFoundException e) {
			result = "OK";
		}
		resultMap.put("result", result);
		resp.setContentType("application/json;charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		try(
			PrintWriter out = resp.getWriter();	
		){
			mapper.writeValue(out, resultMap);
		}
		return null;
	}
}











