package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.Constants;

/**
 * 2020-05-04
 * MIME(Multi-purposed Internet eMail Extenseion) 
 * : 전송데이터 (content) 의 종류 형태 타입에 대한 정의 문자열 
 * : 현재 전송하는 데이터의 종류를 받는쪽에 어떤 형태인지 알려주기 위해서 사용함
 * : 데이터의 수신자의 다음 형태를 알려준다.  
 * : 형식 > main_type/sub_type;charset=encoding ex)text/htmt , text/plain
 *
 */

public class SelectedFileStreamingServlet extends HttpServlet {
	
	private File folder;
	
	private ServletContext application;
	
//	String contentPath = null;
	@Override
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
			//ServletContext application1 = config.getServletContext(); //getServletContext을 찾는 방법 2
			
			application = config.getServletContext();

			System.out.println(this.getClass().getSimpleName()+" 에서 확인 : "+application.hashCode());
			
//			String contentPath = config.getInitParameter("contentPath");
			String contentPath = application.getInitParameter("contentPath");
			if(contentPath==null || contentPath.isEmpty()) {
				throw new ServletException("contentPath 위치 설정 오류 ");
			}
			folder = new File(contentPath);
		}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String filename = req.getParameter("filename");	//클라이언트가 어느 파일을 선택했는지 받아오기 
		
		System.out.println("filename"+filename);
		
		if(filename==null || filename.isEmpty()) {
			resp.sendError(400,"필수 파라미터 누락"); //400에러는 로직에 의해서 에러를 발생시켜야한다. 
			 return;
		}
		//String mime = findMime(filename); 	// 선택한 파일에 따라 mime바꿔주기
		
		//ServletContext application2 =  getServletContext(); //getServletContext을 찾는 방법 1
		//String mime = application2.getMimeType(filename);
		
		// 정상적인 요청이 들어온후 쿠키 생성 
		try {
			String selectedJson = null; 
			Cookie[] cookies = req.getCookies();
			if(cookies!=null) {
				for(Cookie tmp : cookies) {
					if(Constants.SELECTEDFILE.equals(tmp.getName())) {
						selectedJson = URLDecoder.decode(tmp.getValue(), "UTF-8");
					}
				}
			}
			
			String[] newArray = null;
			ObjectMapper mapper = new ObjectMapper();
			
			if(selectedJson!=null) {
				// 이전에 저장된 쿠키가 존재하는 경우
				String[] savedArray = mapper.readValue(selectedJson, String[].class); // 언마샬링 
				newArray = new String[savedArray.length+1];
				System.arraycopy(savedArray, 0, newArray, 0, savedArray.length); // 배열복사 
			}else {
				newArray = new String[1];
			}
			
			newArray[newArray.length-1] = filename;
			selectedJson = mapper.writeValueAsString(newArray);	// 마샬링
			String encodedJson = URLEncoder.encode(selectedJson, "UTF-8");
			
			Cookie selectedCookie = new Cookie(Constants.SELECTEDFILE,encodedJson);
			selectedCookie.setPath(req.getContextPath());
			selectedCookie.setMaxAge(60*60*24*2); //이틀 
			resp.addCookie(selectedCookie);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String mime = application.getMimeType(filename);
		
		resp.setContentType(mime); 
		
		File imgFile = new File(folder, filename);
		byte[] buffer = new byte[1024]; // 한번에 1kb씩 불러들이는 buffer 생성 
		int cnt = -1;
		try(
				FileInputStream fis = new FileInputStream(imgFile);
				OutputStream os = resp.getOutputStream();
				
		){
			while((cnt = fis.read(buffer))!=-1) {
				os.write(buffer, 0, cnt);
			}
		}
	}

	private String findMime(String filename) {
		
		int lastindex = filename.lastIndexOf(".");
		String extension = filename.substring(lastindex+1); // 확장자 
		String mime = null; 
		switch(extension) {
		case "pdf": 
			mime = "application/pdf";
			break;
		case "jpg":
			mime = "image/jpeg";
			break;
		}		
		return mime;
	}
}
