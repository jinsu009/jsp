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
/**
 * Resource 종류 : 자원에 대한 접근 방법에 따라 분류 
 * 알아두는게 좋다 
 * 1. FileSystem Resource : ex) d:/contents/sample.pdf
 * 2. Classpath Resource : ex) kr/or/ddit/sample.pdf
 * 3. Web Resource : ex) http://localhost/context/image/sample.jpg
 * 
 */


// /pdfRead.do 라는 요청에 대한 처리 


// 이전까지 해왔던 방식은 2.5방식 
// java 3.5부터는 annocation사용
// 등록하거나 mapping하거나 한가지 방식만 사용 .xml에 적어둔건 주석 하거나 지워주기 
@WebServlet("/pdfRead.do")
public class PdfStreamServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("application/pdf");

		// 변경되지 않는 경로에 대해 접근해줘야 한다. 
		// 클래스에 먼저 접근해줘야한다. 
		
		// 첫번째 방법 01
//		File pdfFile = new File(PdfStreamServlet.class.getResource("sample.pdf").getPath());
		
		// 첫번째 방법 02
//		File pdfFile =
//				new File(PdfStreamServlet.class.getResource("/kr/or/ddit/servlet01/sample.pdf").getPath());
		
		byte[] buffer = new byte[1024];
		
		int cnt = -1;
		
		try(
				// 첫번째 방법 01,02
//				FileInputStream fis = new FileInputStream(pdfFile);
				
				// 두번째 방법 
				InputStream fis 
				= PdfStreamServlet.class.getResourceAsStream("/kr/or/ddit/servlet01/sample.pdf");
				OutputStream os = resp.getOutputStream();
		){
			while((cnt=fis.read(buffer))!=-1) {
				os.write(buffer,0,cnt);
			}
		}
	}

}
