package kr.or.ddit.student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.student.service.DDITStudentServiceImpl;
import kr.or.ddit.student.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@CommandHandler
public class DDITStudentReadController{

	IDDITStudentService service = DDITStudentServiceImpl.getInstance();
	@URIMapping("/ddit/dditStudents.do")
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
 		PagingVO<DDITStudentVO> pagingVO = new PagingVO<>(5, 3);
 		pagingVO.setSearchVO(searchVO);
 		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readStudentCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<DDITStudentVO> allStudents = service.readStudentList(pagingVO);
		pagingVO.setDataList(allStudents);
		
		request.setAttribute("pagingVO", pagingVO);
		
		return "ddit/dditStudents";
	}
	
	@URIMapping("/ddit/studentView.do")
	public String view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		int status  = 200;
		String goPage = null;
		if(StringUtils.isBlank(code)) {
			status = 400;
		}else {
			try {
				DDITStudentVO vo = service.readStudent(code);
				request.setAttribute("student", vo);
				goPage = "ddit/studentView";
			}catch (DataNotFoundException e) {
				status = 400;
			}
		}
		if(status!=200) {
			response.sendError(status);
		}
		return goPage;
	}
}










