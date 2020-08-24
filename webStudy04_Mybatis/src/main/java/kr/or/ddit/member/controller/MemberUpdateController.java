package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.ModelData;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MemberUpdateController{

	IMemberService service = MemberServiceImpl.getInstance();

	@URIMapping(value="/member/updateMember.do",method=HttpMethod.POST)
	public String update(@ModelData("memVO") MemberVO memVO, HttpServletRequest req
			)  {

//		MemberVO memVO = new MemberVO();
//		req.setAttribute("memVO", memVO);
		// 자격증 처럼 한사람이 여러개의 정보를 가지고 있으니까 , 
		// String[]을 타입에 넣어준다. 
//		Map<String, String[]> memberMap = req.getParameterMap();
//		try {
//			// BeanUtils.populate : 
//			// jsp에서 넘어온값을 간단히 java bean 객체에 맞추어 값을 넣어준다.
//			BeanUtils.populate(memVO, memberMap);
//		} catch (IllegalAccessException | InvocationTargetException e ) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
//			return null;
//		}
		
		CommonValidator<MemberVO> validator = new CommonValidator<>();
		Map<String, List<String>> errors = validator.validate(memVO);
		req.setAttribute("errors", errors);
		
		String goPage = null;
		String msg = null;
		
		if(errors.size()==0) { // 검증이 성공했으면 
			ServiceResult result = service.modifyMember(memVO);
			if (ServiceResult.OK.equals(result)) {
				goPage = "redirect:/mypage.do";
			} else {
				msg = "수정 오류";
				goPage = "member/mypage";
			}
		}else { // 검증실패
//			goPage ="/WEB-INF/views/member/mypage.jsp";
			goPage="member/mypage";
		}
		req.setAttribute("msg", msg);
		return goPage;
	}
	
}
