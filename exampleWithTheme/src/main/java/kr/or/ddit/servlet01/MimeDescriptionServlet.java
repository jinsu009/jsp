package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * MIME (Multi-purposed Internet eMail Extension)
 * : 전송 데이터(content)  의 종류 형태 타입에 대한 정의 문자열.
 * : main_type/sub_type;charset=encoding ex) text/html, text/plain
 */
public class MimeDescriptionServlet extends HttpServlet {
	String contentPath = null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		contentPath = config.getInitParameter("contentP=ath");
		if(contentPath==null || contentPath.isEmpty())
		throw new ServletException("content path 위치 설정 오류");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("image/jpeg");
		File imgFile = new File(contentPath, "prod-1.jpg");
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
}















