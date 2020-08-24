package kr.or.ddit.servlet04;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

// /ddit/contextBrowse.do
/**
 * model2 구조를 사용해서 webapp안의 모든 하위 폴더 목록을 화면의 띄우도록 한다. 
 * 폴더라면 배경색을 주고 파일이라면 배경색을 주지 않는다. 
 * 
 * webapp의 경로 : context root를 불러올수 있어야 한다. 
 * 
 * 상위폴더의 객체를 가져와서 listing 해주기 ? 
 * 
 * 위의 모든 작업은 servlet에서 수행하다. 
 * 
 * jsp 에서는 불러온 모든 파일들의 목록을 꺼내서 보여주면 된다. 
 * 
 * 요약은 탐색기 만들기 
 *
 */

/**
 * servlt : request분석하고 contents를 생성 (scope 사용)
 */

// 요청 접수
@WebServlet("/ddit/contextBrowse.do")
public class ContextBrowsingServlet extends HttpServlet{
	
	private File folder;
	private ServletContext application;

	@Override
	public void init() throws ServletException {
		super.init();
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	/*
	 	resp.setContentType("text/html;charset=UTF-8");
		application = getServletContext();
		String realpath = application.getRealPath("");
		System.out.println("realpath" + realpath);
		folder = new File(realpath);
		String[] fname = folder.list();
		for(String i : fname) {	System.out.println(i); }
		req.setAttribute(realpath, fname);
		req.getRequestDispatcher("/ddit/browse.jsp").forward(req, resp);
	*/
//----------------------------------------------------------------------------------
		// 요청 분석
		
		String accept = req.getHeader("Accept");
		
		// contents 생성 ,, 서버사이드 코드 루트 : '/'
			String rootURL = "/";
			String param = req.getParameter("base");
			
			if(StringUtils.isNotBlank(param)) {
				rootURL=param;
			}
			String realPath = application.getRealPath(rootURL);
			File rootFolder = new File(realPath);
			
			// 폴더 검증
			if(!rootFolder.exists() || rootFolder.isFile()) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"존재하지 않거나 파일입니다.");
				return;
			}
			
			File[] childArr = rootFolder.listFiles();
			
			FileWrapper[] wrapperArr = new FileWrapper[childArr.length];
			for(int i=0; i<childArr.length; i++) {
				wrapperArr[i] = new FileWrapper(childArr[i],application);
			}
			
		// contents 전달. 공유 ,, 현재 클라이언트가 json을 요구하는지 html을 요구하는지 알아야 한다.  
			req.setAttribute("childArr", wrapperArr);
			if(accept.contains("json")) {
				resp.setContentType("application/json;charset=UTF-8");
				// marshalling (마샬링) : java데이터를 json으로 변환
//					[{"name":"ddit","id":"/WEB-INF/views/ddit"}]
				ObjectMapper mapper = new ObjectMapper(); // 마샬링, 언마샬링 해주는 메소드 
				String json = mapper.writeValueAsString(wrapperArr);
				
				// serialization (직렬화)
				try(
					PrintWriter out = resp.getWriter();
				){
					out.print(json);
				}
			}else {
				// view layer 결정
				String goPage = "/WEB-INF/views/ddit/browse.jsp";
				req.getRequestDispatcher(goPage).include(req, resp);
			}
			
	}
}
