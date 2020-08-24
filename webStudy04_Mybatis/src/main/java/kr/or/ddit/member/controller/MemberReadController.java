package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.ModelData;
import kr.or.ddit.mvc.stereotype.RequestParameter;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

/**
 * 회원 관리를 위한 controller layer
 * 목록 조회 : /member/memberList.do
 * 상세 조회 : /member/memberView.do (2020.05.26)
 * 신규 등록 : /member/insertMember.do
 * 수정 : /member/updateMember.do
 * 탈퇴 : /member/deleteMember.do
 *
 */
//@WebServlet("/member/memberList.do")
@CommandHandler
public class MemberReadController{
	private static final long serialVersionUID = 1L;
	
	// logger 잡기 
	private static Logger logger = LoggerFactory.getLogger(MemberReadController.class);

	IMemberService service = MemberServiceImpl.getInstance();
	
	@URIMapping("/member/memberList.do")
	public String list(
			@RequestParameter(name="page", required = false, defaultValue = "1") int currentPage, 
			@ModelData("searchVO") SearchVO searchVO,
			HttpServletRequest request)
			{

//		String pageParam = request.getParameter("page");
//		String searchType = request.getParameter("searchType");
//		String searchWord = request.getParameter("searchWord");
		
//		logger.info("전달된 파라미터 : currentPage : {}, searchType : {}, searchWord :{}"
//				,pageParam,searchType,searchWord);
		// log4j 는 formatting하기 쉽다.

//		SearchVO searchVO = new SearchVO();
//		searchVO.setSearchType(searchType);
//		searchVO.setSearchWord(searchWord);

//		int currentPage = 1;
//		if (StringUtils.isNumeric(pageParam)) {
//			currentPage = Integer.parseInt(pageParam);
//		}
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 3); // 한페이지당 5개 , 페이지 수 3개 (3개이상이면 next 버튼 활성화)
		pagingVO.setSearchVO(searchVO);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<MemberVO> memberList = service.readMemberList(pagingVO);
		pagingVO.setDataList(memberList);
	
		
		request.setAttribute("pagingVO", pagingVO);
		return "member/memberList";
		
		// 마샬링 , 직렬화 
//		if(request.getHeader("Accept").contains("json")) {
//			response.setContentType("application/json;charset=UTF-8");
//			ObjectMapper mapper = new ObjectMapper();
//			try(
//				PrintWriter out = response.getWriter();
//			){
//				mapper.writeValue(out, pagingVO);
//			}
//			return null;
//		}else {
//			request.setAttribute("pagingVO", pagingVO);
//			return "member/memberList";
//		}
		
	}
	
	@URIMapping("/member/memberView.do")
	public String view(@RequestParameter(name="who", required=true) String mem_id, HttpServletRequest req) {
		
		// 우리가 받고자 하는 데이터가 필수 데이터인지 아닌지 확인이 필요하다 - handler invoker
		// required = false : 검증하지 않아도 되지만 default value 가 필요하다 
		
		MemberVO memVO = service.readMember(mem_id);
		req.setAttribute("memVO", memVO);
		String goPage = "member/memberView";
		return goPage;
	}
}
