package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MemberInsertController{
	IMemberService service = MemberServiceImpl.getInstance();
	
	@URIMapping(value="/member/insertMember.do", method=HttpMethod.GET)
	public String form(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "member/memberForm";
	}
	
	@URIMapping(value="/member/insertMember.do", method=HttpMethod.POST)
	public String insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO  member = new MemberVO();
		req.setAttribute("member", member);
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			resp.sendError(400, e.getMessage());
			return null;
		}
		
		Map<String, String> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);
		String goPage = null;
		String message = null;
		if(valid) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				goPage = "member/memberForm";
				message = "아이디 중복";
				break;
			case FAIL:
				goPage = "member/memberForm";
				message = "서버 오류";
				break;

			default: // OK
				goPage = "commons/resultPage";
				break;
			}
		}else {
			goPage = "member/memberForm";
		}
		
		req.setAttribute("message", message);
		
		return goPage;
	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		// 검증 룰
		if (StringUtils.isBlank(member.getMem_id())) {
			valid = false;
			errors.put("mem_id", "회원아이디 누락");
		}
		if (StringUtils.isBlank(member.getMem_pass())) {
			valid = false;
			errors.put("mem_pass", "비밀번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_name())) {
			valid = false;
			errors.put("mem_name", "회원명 누락");
		}
		if (StringUtils.isBlank(member.getMem_regno1())) {
			valid = false;
			errors.put("mem_regno1", "주민번호1 누락");
		}
		if (StringUtils.isBlank(member.getMem_regno2())) {
			valid = false;
			errors.put("mem_regno2", "주민번호2 누락");
		}
		if (StringUtils.isBlank(member.getMem_zip())) {
			valid = false;
			errors.put("mem_zip", "우편번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_add1())) {
			valid = false;
			errors.put("mem_add1", "주소1 누락");
		}
		if (StringUtils.isBlank(member.getMem_add2())) {
			valid = false;
			errors.put("mem_add2", "주소2 누락");
		}
		if (StringUtils.isBlank(member.getMem_hometel())) {
			valid = false;
			errors.put("mem_hometel", "집전화번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_comtel())) {
			valid = false;
			errors.put("mem_comtel", "회사전화번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_mail())) {
			valid = false;
			errors.put("mem_mail", "이메일 누락");
		}
		return valid;
	}
}








