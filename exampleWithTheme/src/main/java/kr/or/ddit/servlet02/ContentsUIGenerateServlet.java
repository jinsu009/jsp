package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.TemplateUtils;

/**
 * d:/contents 폴더의 파일 목록을 select 태그로 완성.
 * 파일의 목록에는 ico 파일을 포함시키지 말것.
 *   /contents.do 요청에 의해 UI 가 제공됨.
 *  단, 절대로 어떤 경로도 소스상에 하드코딩 하지 말것. 
 *
 */
// CoC(Convention Over Configuration)
//@WebServlet(initParams= {@WebInitParam(name="contentsPath", value="d:/contents")}, 
//			urlPatterns="/contents.do", loadOnStartup=1)
public class ContentsUIGenerateServlet extends HttpServlet{
	
	private File folder;
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		System.out.println(this.getClass().getSimpleName() +"에서 확인 :"+ application.hashCode());
		String contentsPath = application.getInitParameter("contentsPath");
		if(contentsPath==null || contentsPath.isEmpty()) throw new NullPointerException("contentsPath 파라미터 누락");
		folder = new File(contentsPath);
		Calendar cal = getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String[] childArray = folder.list((dir, name)->{
			boolean result = !name.endsWith(".ico");
			return result;
		});
		
		StringBuffer data = makeData(childArray);
		Map<String, Object> dataMap = new LinkedHashMap<>();
		dataMap.put("data", data);
		dataMap.put("now", new Date());
		String html = TemplateUtils.makeHTML("/kr/or/ddit/servlet02/samleTemplate.tmpl", dataMap);
		html+=", "+getServletContext().hashCode();
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(html);
		}
	}

	private StringBuffer makeData(String[] childArray) {
		StringBuffer data = new StringBuffer();
		for(String child : childArray) {
			data.append(String.format("<option value='%1$s'>%1$s</option>", child));
		}
		return data;
	}
}













