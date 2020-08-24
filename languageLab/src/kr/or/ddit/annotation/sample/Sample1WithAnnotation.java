package kr.or.ddit.annotation.sample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.annotation.stereotype.CommandHandler;
import kr.or.ddit.annotation.stereotype.SecondSingleValueAnnotation;
import kr.or.ddit.annotation.stereotype.URIMapping;
import kr.or.ddit.annotation.stereotype.URIMapping.HttpMethod;

@CommandHandler
public class Sample1WithAnnotation {

	// value라는 속성명은 생략이가능하다. 
	@SecondSingleValueAnnotation("값1")
	
	@URIMapping(value="text1",method=HttpMethod.POST)
	public void test1() {
		// 어떤 요쳥이 post 방식으로 들어왔을 때 처리 될 메소드
		// callback 까지 포함되어 있다.
	}
	
	@SecondSingleValueAnnotation("값2")
	@URIMapping("/member/memberList.do")
	public String test2(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("요청분석 > " + req);
		return "member/memberList";
	}
}
