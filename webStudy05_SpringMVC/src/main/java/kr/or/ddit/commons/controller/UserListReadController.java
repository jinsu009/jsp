package kr.or.ddit.commons.controller;

import java.io.IOException;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.vo.MemberVO;

//2020-06-11,12
@RestController
public class UserListReadController {

	// 컨테이너에 종속된 bean은 webapplication으로 가져온다.
	@Inject
	WebApplicationContext container;
	ServletContext application;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	@GetMapping(value = "/getUserList.do",produces ="application/json;charset=UTF-8" )
	@ResponseBody
	public Set<MemberVO> userList() throws IOException, ServletException {
		Set<MemberVO> userList = (Set<MemberVO>) application.getAttribute("userList");
		
		return userList; 
		// 마샬링을 하기 떄문에 view에대한정보는 return 시켜주지 않는다. > @ResponseBody
		// 마샬링할 대상을 return으로 보내준다. 
	}
}
