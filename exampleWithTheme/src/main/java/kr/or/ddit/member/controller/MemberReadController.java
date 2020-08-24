package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;


/**
 * 회원 관리를 위한 controller layer
 * 목록 조회 : /member/memberList.do(*)
 * 상세 조회 : /member/memberView.do
 * 신규 등록 : /member/insertMember.do
 * 수정 : /member/updateMember.do
 * 탈퇴 : /member/deleteMember.do
 *
 */
@CommandHandler
public class MemberReadController{
	private static Logger logger = LoggerFactory.getLogger(MemberReadController.class);
  
	IMemberService service = MemberServiceImpl.getInstance();
	@URIMapping("/member/memberList.do")
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		String pageParam = request.getParameter("page");
 		String searchType = request.getParameter("searchType");
 		String searchWord = request.getParameter("searchWord");
 		logger.info("전달된 파라미터 : currentPage: {}, searchType: {}, searchWord: {}", 
 						pageParam, searchType, searchWord);
 		SearchVO searchVO = new SearchVO();
 		searchVO.setSearchType(searchType);
 		searchVO.setSearchWord(searchWord);
 		
 		int currentPage = 1;
 		if(StringUtils.isNumeric(pageParam)) {
 			currentPage = Integer.parseInt(pageParam);
 		}
 		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 3);
 		pagingVO.setSearchVO(searchVO);
 		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<MemberVO> memberList = service.readMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		request.setAttribute("pagingVO", pagingVO);
		// logical view name
		String goPage = "member/memberList";
		return goPage;
	}
	
	@URIMapping("/member/memberView.do")
	public String view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = req.getParameter("who");
		if(StringUtils.isBlank(mem_id)) {
			resp.sendError(400, "필수 파라미터 누락");
			return null;
		}
		MemberVO member = service.readMember(mem_id);
		req.setAttribute("member", member);
		String goPage = "member/memberView";
		return goPage;
	}
}




