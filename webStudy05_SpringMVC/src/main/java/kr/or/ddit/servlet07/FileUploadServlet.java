package kr.or.ddit.servlet07;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet{
	
	ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String uploader = req.getParameter("uploader");
		
		// 파트명을 모를때 
		Collection<Part> parts = req.getParts();
		Iterator<Part> it = parts.iterator();
		Map<String, List<File>> partMap = new HashMap<>();
		while(it.hasNext()) {
			Part part = (Part) it.next();
			// 파일 데이터인지 문자열인지 구분이 필요하다 
			String header = part.getHeader("Content-Disposition");
			if(header==null || header.indexOf("filename")==-1) {
				continue;
			}
			
			
			String partName = part.getName();
			List<File> fileList = partMap.get(partName);
			if(fileList==null) {
				fileList = new ArrayList<>();
				partMap.put(partName, fileList);
			}
			try {
				File saveFile = fileUploadProcess(part);
				if(saveFile!=null) {
					fileList.add(saveFile);
				}
			}catch(RuntimeException e) {
				resp.sendError(400,e.getMessage());
				return;
			}
		}
		System.out.printf("uploader : %s\n",uploader);
		List<File> fileList = partMap.get("uploadFile");
		for(File saveFile : fileList) {
			System.out.printf("uploadFile filename : %s , exist : %s  \n",saveFile.getName(), saveFile.exists());
		}
		
		// jsp에서 저장된 이미지 불러오기
		req.getSession().setAttribute("fileList", fileList); 
		resp.sendRedirect(req.getContextPath() + "/11/fileUploadForm.jsp");
		
	}
	private File fileUploadProcess(Part uploadFilePart) throws IOException{
		
		String fileMime = uploadFilePart.getContentType();
		if(fileMime==null || !fileMime.startsWith("image/")) {
			throw new RuntimeException("이미지 형식이 아니다.");
		}
		
		long filesize = uploadFilePart.getSize();
		String partName = uploadFilePart.getName(); // input 태그의 name속성 값
		
//		Content-Disposition: form-data; name="uploadFile"; filename="back_0.jpg"
		String header = uploadFilePart.getHeader("Content-Disposition");
		int index = header.indexOf("filename");
		if(index == -1) {
			// 파일을 선택하지 않은 상태로 submit : 업로드가 안되고있음
			System.out.println("선택된 파일이 없다.");
			return null;
		}
		int secondIndex = header.indexOf("=", index);
		header = header.substring(secondIndex+1);
		String filename = header.replace("\"", "");
		
		// 저장위치(images폴더) , 저장명 정하고  string copy
		String saveFolderUrl = "/images";
		String saveFolderPath = application.getRealPath(saveFolderUrl);
		File saveFolder = new File(saveFolderPath);	
		File saveFile = new File(saveFolder,filename);
		
		try(
			// 이진데이터 복사 
			InputStream is = uploadFilePart.getInputStream(); // 입력 스트림
			FileOutputStream fos = new FileOutputStream(saveFile);
		){
			// 복사
			byte[] buffer = new byte[1024];
			int pointer = -1;
			while((pointer = is.read(buffer))!=-1) {
				fos.write(buffer,0, pointer);
			}
		}
		return saveFile;
	}
}
