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
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/idValidate.do")
@CommandHandler
public class IdValidateServlet{

	IMemberService service = MemberServiceImpl.getInstance();

	@URIMapping(value="/member/idValidate.do",method=HttpMethod.POST)
	public String validate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 클라이언트가 보낸 아이디를 잡아서 중복을 됐는지 안됐는지 확인해준다. 
		// 비동기 요청인지 동기 요청인지 생각해가면서 코드 짜기
		
		// 클라이언트가 새로입력한 아이디와 member 테이블 안에 있는 아이디 비교 
		// 중복된 아이디가 있으면 '아이디 중복' 없으면 '사용가능한 아이디'
		String memid = req.getParameter("memid");

		// 아이디 검증 
		if(StringUtils.isBlank(memid)) {
			resp.sendError(400,"아이디 누락");
			return null;
		}
		
		PrintWriter out = resp.getWriter();
		
		MemberVO memvo = service.readMember(memid);
		// member 테이블에 있는 모든 아이디들을 가지고 와야 한다. .. ㅇㅂㅇ. . 
				
		if(memvo!=null) {
			// 존재하는 회원, 아이디 중복
			out.print("1");
		}else {
			// 사용가능한 아이디
			out.print("0");
		}
		// -------------- 선생님 
//		Map<String , Object> resultMap = new HashMap<>();
//		String result = null;
//		try {
//			service.readMember(memid);
//			result = "DUPLICATE";
//		} catch (DataNotFoundException e) {
//			result = "OK";
//		}
//		resultMap.put("result", result);
//		resp.setContentType("application/json;charset=UTF-8");
//		ObjectMapper mapper = new ObjectMapper();
//		try(
//			PrintWriter out2 = resp.getWriter();
//		){
//			mapper.writeValue(out2, resultMap);
//		}		
		
		return null;
		
	}
	
}
