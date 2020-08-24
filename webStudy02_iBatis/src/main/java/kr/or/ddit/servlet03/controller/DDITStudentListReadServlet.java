package kr.or.ddit.servlet03.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.servlet03.service.DDITStudentServiceImpl;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;


@WebServlet("/ddit/dditStudents.do")
public class DDITStudentListReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IDDITStudentService service = DDITStudentServiceImpl.getInstance();
       
	// 요청을 받는 역할 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// jsp로 이동하기 전에 dao에 있는 데이터를 확인 하고 넘어가야한다. 
		
		request.setCharacterEncoding("UTF-8");
		
		String pageParam = request.getParameter("page");
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		SearchVO searchVO = new SearchVO();
		searchVO.setSearchType(searchType);
		searchVO.setSearchWord(searchWord);
		
		int currentPage = 1; 
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<DDITStudentVO> pagingVO = new PagingVO<>(5,3);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readStudentCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<DDITStudentVO> allStudents = service.readStudentList(pagingVO);
		
		pagingVO.setDataList(allStudents);
		
		request.setAttribute("pagingVO", pagingVO);
		
		String goPage = "/WEB-INF/views/ddit/dditStudents.jsp";
		request.getRequestDispatcher(goPage).forward(request, response);
	}
}
