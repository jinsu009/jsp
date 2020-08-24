package kr.or.ddit.servlet04;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

//		1. 요청 접수
@WebServlet("/ddit/contextBrowse.do")
public class ContextBrowsingServlet extends HttpServlet{
	
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		2. 요청 분석
		String accept = req.getHeader("Accept");
//		3. contents 생성
		String rootUrl = "/";
		String param = req.getParameter("base");
		if(StringUtils.isNotBlank(param)) {
			rootUrl = param;
		}
		String realPath = application.getRealPath(rootUrl);
		File rootFolder = new File(realPath);
		if(!rootFolder.exists() || rootFolder.isFile()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "존재하지않거나, 파일입니다.");
			return;
		}
		File[] childArray = rootFolder.listFiles();
		FileWrapper[] wrapperArray = new FileWrapper[childArray.length];
		for(int i=0; i<childArray.length; i++) {
			wrapperArray[i] = new FileWrapper(childArray[i], application);
		}
//		4. contents 전달(공유)
		req.setAttribute("childArray", wrapperArray);
		if(accept.contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
//			1) marshalling
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(wrapperArray);
//			2) serialization
			try(
				PrintWriter out = resp.getWriter();
			){
				out.print(json);
			}		
		}else {
//		5. view layer 결정
			String goPage = "/WEB-INF/views/ddit/browse.jsp";
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
//		
	}
}







