package kr.or.ddit.member.controller;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.WebAppTestContext;

@RunWith(SpringRunner.class)
@WebAppTestContext
public class MemberReadControllerTest {

	@Inject
	WebApplicationContext context;
	MockMvc mockMvc;
	
	@Before // case보다 먼저 실행 하고 싶을 때 
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		/**
		 * 테스트 하는 도중에는 서버를 돌리지 않으니까 DS가 없어서 controller 작동이 어렵기 때문에 가상의 DS를 만들어주기 위해 사용한다. 
		 */
	}
	/**
	 * 1. 사용하고 있는 view name이 알맞은지 test
	 * 2. model data 확인
	 */
	
	@Test
	public void testList() throws Exception{
		mockMvc.perform(get("/member/memberList.do").param("page", "2")
													.param("searchType", "address")
													.param("serarchWord", "대전")
													).andExpect(status().isOk()) // 명화한 상태코드를 몰라도 테스트 할수 있도록 
													.andExpect(model().attributeExists("pagingVO")) // 어떤 속성데이터가 존재하는지 확인
													.andExpect(view().name("member/memberList")) // logical view name
													.andDo(log()) // 응답 데이터가 나오면 응답데이터로 어떤 일을 수행할지 정함
													.andReturn();
		/**
		 * 가상의 전송방식 : get 
		 */
	}

	@Test
	public void testView() throws Exception {
		mockMvc.perform(get("/member/memberView.do"))
							.andExpect(status().isBadRequest())
							.andDo(log())
							.andReturn();
	}

}
