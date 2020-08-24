package kr.or.ddit.servlet05;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/FileMCD")
public class FileMCD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ServletContext application;

	@Override
	public void init() throws ServletException {
		super.init();
		application = getServletContext();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 선택된 파일의 경로 가져오기
		// 선택된 폴더의 경로 가져오기
		// 선택된파일을 선택된 폴더의 경로로 이동시켜주기 

		//-----------------------------------------
		String fileid = req.getParameter("fileid");
		String radioval = req.getParameter("radioval");
		
		System.out.println("fileid"+fileid);
		System.out.println("radioval" + radioval);
		//------------------------------------------
		Path a =  Paths.get(application.getRealPath(fileid));
 		Path b = Paths.get(application.getRealPath(radioval));
 		Files.move(a, b.resolve(a.getFileName()));
	}
}
