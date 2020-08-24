package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

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
 * 
 * MIME (Multi-purposed Internet eMail Extension)
 * : 전송 데이터(content)  의 종류 형태 타입에 대한 정의 문자열.
 * : main_type/sub_type;charset=encoding ex) text/html, text/plain
 */
public class SelectedFileStreamingServlet extends HttpServlet {
	private File folder;
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = config.getServletContext(); 
		System.out.println(this.getClass().getSimpleName() +"에서 확인 :"+ application.hashCode());
		String contentPath = application.getInitParameter("contentsPath");
		if(contentPath==null || contentPath.isEmpty())
		throw new ServletException("content path 위치 설정 오류");
		folder = new File(contentPath);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String filename = req.getParameter("filename");
		if(filename==null||filename.isEmpty()) {
			resp.sendError(400, "필수파라미터 누락");
			return;
		}
		
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
			String[] savedArray = mapper.readValue(selectedJson, String[].class);
			newArray = new String[savedArray.length+1];
			System.arraycopy(savedArray, 0, newArray, 0, savedArray.length);
		}else {
			newArray = new String[1];
		}
		newArray[newArray.length-1] = filename;
		selectedJson = mapper.writeValueAsString(newArray);
		String encodedJson = URLEncoder.encode(selectedJson, "UTF-8");
		Cookie selectedCookie = new Cookie(Constants.SELECTEDFILE, encodedJson);
		selectedCookie.setPath(req.getContextPath());
		selectedCookie.setMaxAge(60*60*24*2);
		resp.addCookie(selectedCookie);
		
		String mime = application.getMimeType(filename);
		
		resp.setContentType(mime);
		File imgFile = new File(folder, filename);
		byte[] buffer = new byte[1024];
		int cnt = -1;
		
		try(
			FileInputStream fis = new FileInputStream(imgFile);
			OutputStream os = resp.getOutputStream();
		){
			while((cnt=fis.read(buffer))!=-1) {
				os.write(buffer, 0, cnt);
			}
		}
	}

	private String findMime(String filename) {
		int lastIndex = filename.lastIndexOf(".");
		String extension = filename.substring(lastIndex+1);
		String mime = null;
		switch (extension) {
		case "pdf":
			mime = "application/pdf";
			break;
		case "jpg":
			mime = "image/jpeg";
			break;

		default:
			break;
		}
		return mime;
	}
}















