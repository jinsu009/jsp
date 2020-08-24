package kr.or.ddit.servlet03.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;


//@WebServlet("/ddit/dditStudents.do")
@Controller
@RequestMapping(value = "/ddit/dditStudents.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DDITStudentListReadServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	IDDITStudentService service;
       
	// 요청을 받는 역할 
	@GetMapping
	public String doGet(
			@RequestParam(name="page",required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO,
			Model model
			) {
		// jsp로 이동하기 전에 dao에 있는 데이터를 확인 하고 넘어가야한다. 
		
//		String pageParam = request.getParameter("page");
//		String searchType = request.getParameter("searchType");
//		String searchWord = request.getParameter("searchWord");
//		
//		SearchVO searchVO = new SearchVO();
//		searchVO.setSearchType(searchType);
//		searchVO.setSearchWord(searchWord);
		
//		int currentPage = 1; 
//		if(StringUtils.isNumeric(pageParam)) {
//			currentPage = Integer.parseInt(pageParam);
//		}
		
		PagingVO<DDITStudentVO> pagingVO = new PagingVO<>(5,3);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.readStudentCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<DDITStudentVO> allStudents = service.readStudentList(pagingVO);
		
		pagingVO.setDataList(allStudents);
		
		model.addAttribute("pagingVO", pagingVO);
		
		String goPage = "ddit/dditStudents";
		return goPage;
	}
}
