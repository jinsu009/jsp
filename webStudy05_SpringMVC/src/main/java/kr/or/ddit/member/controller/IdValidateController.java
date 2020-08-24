package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

// 20-06-12
@RestController  //스프링 버전 4.0이후 사용가능 , 이 컨트롤러안의 모든 데이터는 응답데이터를  json이나 xml로 내보내야 사용할 수 있따. (controller + responsebody)
@RequestMapping(value = "/member/idValidate.do", produces = "application/json;charset=UTF-8")
public class IdValidateController{

	@Inject
	IMemberService service;

	@PostMapping
	public String validate(
			 @RequestParam(required = true) String memid
			 , HttpServletResponse resp
			) throws IOException
	{
		// 클라이언트가 보낸 아이디를 잡아서 중복을 됐는지 안됐는지 확인해준다. 
		// 비동기 요청인지 동기 요청인지 생각해가면서 코드 짜기
		
		// 클라이언트가 새로입력한 아이디와 member 테이블 안에 있는 아이디 비교 
		// 중복된 아이디가 있으면 '아이디 중복' 없으면 '사용가능한 아이디'

		
		PrintWriter out = resp.getWriter();
		
		MemberVO memvo = service.readMember(memid);
				
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
//		 return resultMap;   //이미 상태코드가 나갔으므로c
		
		return null;
		
	}
	
}
