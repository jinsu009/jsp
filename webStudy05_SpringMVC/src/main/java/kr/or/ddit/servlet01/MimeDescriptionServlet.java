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
 * 2020-05-04
 * MIME(Multi-purposed Internet eMail Extenseion) 
 * : 전송데이터 (content) 의 종류 형태 타입에 대한 정의 문자열 
 * : 현재 전송하는 데이터의 종류를 받는쪽에 어떤 형태인지 알려주기 위해서 사용함
 * : 데이터의 수신자의 다음 형태를 알려준다.  
 * : 형식 > main_type/sub_type;charset=encoding ex)text/htmt , text/plain
 *
 */

public class MimeDescriptionServlet extends HttpServlet {
	
	String contentPath = null;
	@Override
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
			contentPath = config.getInitParameter("contentPath");
			// 만약 null값이 들어올때 어떻게 처리 해줘야 할까 .. 그때 우리는 throw exception 을 발생시켜준다.
			if(contentPath==null || contentPath.isEmpty()) {
				throw new ServletException("contentPath 위치 설정 오류 ");
			}
		}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 이미지를 담아서 제공해주는 서버사이드매개체가 필요하다 
		// 서버측에 잇는 이미지를 스트림의 형태로 담아서 가져와야한다. 
		// 스트림데이터를 클라이언트에게 내보내 줘야 한다. >> streamcopy
		//==============
		// mime 텍스트를 응답데이터에 포함시켜 내보내야 한다. 
		// mime의 확장자명에 어떻게 적어줄건지 인터넷이ㅔ 검색해서 찾아보자 
		// mime를 설정할땐 출력코드보다 먼저 나와야하므로 주로 맨위에 작성해준다. 
		resp.setContentType("image/jpeg"); 

		//==============
		//1. io > 데이터의 소스를 자바 형태로 만들어줘야 한다
//		File imgFile = new File("d:/contents/pinkcloud.jpg");
		File imgFile = new File(contentPath, "pinkcloud.jpg");
	
		byte[] buffer = new byte[1024]; 
		// 한번에 1kb씩 불러들이는 buffer 생성 
		
		int cnt = -1;
		
		try(
				FileInputStream fis = new FileInputStream(imgFile);
				// 이미지 파일을 불러오기 위해서 inputstream을 사용한다. 
				OutputStream os = resp.getOutputStream();
				// 읽을때 1 byte 니까 내보낼때도 1byte
				
		){
			while((cnt = fis.read(buffer))!=-1) {
				// 퍄일 기록 
				// buffer 을 0부터 cnt까지 저장시킨다. 
				os.write(buffer, 0, cnt);
			}
		}
	}
}
