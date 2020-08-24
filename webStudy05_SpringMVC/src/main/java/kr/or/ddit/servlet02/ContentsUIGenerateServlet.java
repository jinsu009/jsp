package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
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
 * d:/contents 폴더의 파일목록을 select태그로 완성 
 * 파일의 목록에는 ico 파일을 포함시키지 말 것 
 * contents 파일아래에있는 목록들을 필터링 해줘야한다. ai ?
 * 	/contents.do 요청에 의해 UI가 제공됨
 * 단, 절대로 어떤 경로도 소스상에 하드코딩 하지 말것 
 * xml 파라미터값 넘겨주기  
 *
 */

//CoC(Convention Over Configuration) : 
//web.xml 에서는 servlet-name을 작성하지만 여기서는 작성하지 않는다....???
//@WebServlet(initParams={@WebInitParam(name="contentsPath",value="d:/contents")},
//urlPatterns="/contents.do", loadOnStartup=1)
public class ContentsUIGenerateServlet extends HttpServlet{
	
	// 1. 초기화 파리미터를 잡는다. > 초기화가 발생하는 시점 init()
	// 2. 파일객체생성
	// 3. doget 에서 ico파일은 호출 하지 않도록 
	// 4. streagbuffer로 html 작성
	// 5. mime는 출력스트림 설정전에 해야하므로 위에서 작성해준다.	
	// 6. 선택된 파일을 인터넷창에 띄위야 하는데 req의 역할을 알아야한다. 
	// 7. 파일을 selcted클래스로 넘겨줄려면 form태그를 사용해서 값을 전송해야한다
	// 8. 버튼의 action속성이 필요
	private File folder;
	private ServletContext application;

		@Override
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
			application = getServletContext();
			
			System.out.println(this.getClass().getSimpleName()+" 에서 확인 : "+application.hashCode());
			
//			String contentPath = config.getInitParameter("contentPath");
			String contentPath = application.getInitParameter("contentPath");
					
			
			if(contentPath==null || contentPath.isEmpty()) {
				throw new NullPointerException("contentPath 파라미터 누락");
			}			
			folder = new File(contentPath);
			
			System.out.println("content : " + contentPath);
			
//			ServletContext application3 = config.getServletContext(); //getServletContext을 찾는 방법 2
			// application1~3 : 은 같은 객체 이다. 
			
			
			//=================
			Calendar cal = Calendar.getInstance();
		}
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			resp.setContentType("text/html;charset=UTF-8");
			
			String[] childArray = folder.list((dir, name)->{
				boolean result = !name.endsWith(".ico");
				return result;
			});
			// 람다식을 이용 , dir, name을 받아서 boolean을 반환
			// childArray : 모든 파일들이 담겨진다. 
			
			// classpath resources에 접근하기위한 변수와 메소드 생성 
			// 같은 폴더안에 위치해있으니까 상대경로로 작성해준다 
			
			// 사용될 메소드 생성
			// 경로에 작성된 tmpl 텍스트를 가져오고 html 에 담아준다. 
			// @를기준으로 indexof 하여 텍스트를 replace해주고 
			// 클라이언트가 선택한 데이터가 넘어가도록 한다. 
			
			// 1. map 생성 
			// 2. datamap을 생성하고 값을 넣어준다. 
			// 3. datamap.put
			// 4. data가 변경된후 now가 변경되야 한다. 
			// 5. 코드 치환 
			// 6. for문 으로 map 출력 
			
			StringBuffer data = makeData(childArray);
			
			Map<String, Object> dataMap = new LinkedHashMap<>();
			dataMap.put("data",data);
			dataMap.put("now",new Date());
			
//			String html = TemplateUtils.makeHTML("kr/or/ddit/servlet02/sampleTemplate.tmpl", dataMap);
			String html = TemplateUtils.makeHTML("sampleTemplate.tmpl", dataMap);
			
			html += ","+getServletContext().hashCode();
			
			System.out.println("html >> "+html);
			
			try(
				PrintWriter out = resp.getWriter();
			){
				out.println(html);
			}
		}

		private StringBuffer makeData(String[] childArray) {
			StringBuffer data = new StringBuffer();
			for(String child : childArray) {
				data.append(String.format("<option value='%1$s'>%1$s</option>",child));
			}
			return data;
		}
	
}
