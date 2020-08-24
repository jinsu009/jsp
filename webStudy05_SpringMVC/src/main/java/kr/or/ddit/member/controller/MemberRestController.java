package kr.or.ddit.member.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.MemberVO;

@RestController
@RequestMapping("/member")
public class MemberRestController {

	@GetMapping
	public List<MemberVO> list() {
		return null;

	}

	@GetMapping("a001") // /member/a001
	public MemberVO oneMember() {
		return null;
	}

	@PostMapping
	public void insert(@RequestBody MemberVO member) {
	}

	@PutMapping
	public void update(@RequestBody MemberVO member) {

	}
	// 클라이언트가 보내는 값을 언마샬링 해서 받기 위해 requestbody로 받는다.
	
	@DeleteMapping("a001") // /member/a001
	public void delete() {
		
	}
}
