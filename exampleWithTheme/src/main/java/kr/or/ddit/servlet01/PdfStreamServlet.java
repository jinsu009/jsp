package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// /pdfRead.do 라는 요청에 대한 처리
/**
 * Resource 종류 : 자원에 대한 접근 방법에 따라 분류
 * 1. FileSystem Resource : ex) d:/contents/sample.pdf
 * 2. Classpath Resource : ex) kr/or/ddit/sample.pdf
 * 3. Web Resource : ex) http://localhost/context/image/sample.jpg
 *
 */
@WebServlet("/pdfRead.do")
public class PdfStreamServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		File pdfFile = 
//		new File(PdfStreamServlet.class.getResource("/kr/or/ddit/servlet01/sample.pdf").getPath());
		resp.setContentType("application/pdf");
		byte[] buffer = new byte[1024];
		int cnt = -1;
		try(
			InputStream fis = PdfStreamServlet.class.getResourceAsStream("/kr/or/ddit/servlet01/sample.pdf");
			OutputStream os = resp.getOutputStream();
		){
			while((cnt=fis.read(buffer))!=-1) {
				os.write(buffer, 0, cnt);
			}
		}
	}
}