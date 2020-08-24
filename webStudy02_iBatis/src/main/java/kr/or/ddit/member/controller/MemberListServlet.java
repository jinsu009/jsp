package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
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
@WebServlet("/member/memberList.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// logger 잡기 
	private static Logger logger = LoggerFactory.getLogger(MemberListServlet.class);

	IMemberService service = MemberServiceImpl.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String pageParam = request.getParameter("page");
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		logger.info("전달된 파라미터 : currentPage : {}, searchType : {}, searchWord :{}"
				,pageParam,searchType,searchWord);
		// log4j 는 formatting하기 쉽다.

		SearchVO searchVO = new SearchVO();
		searchVO.setSearchType(searchType);
		searchVO.setSearchWord(searchWord);

		int currentPage = 1;
		if (StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 3); // 한페이지당 5개 , 페이지 수 3개 (3개이상이면 next 버튼 활성화)
		pagingVO.setSearchVO(searchVO);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<MemberVO> memberList = service.readMemberList(pagingVO);

		pagingVO.setDataList(memberList);

		request.setAttribute("pagingVO", pagingVO);

		String goPage = "/WEB-INF/views/member/memberList.jsp";
		request.getRequestDispatcher(goPage).forward(request, response);
	}
}
