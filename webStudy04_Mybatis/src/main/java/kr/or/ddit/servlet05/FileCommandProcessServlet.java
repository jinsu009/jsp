package kr.or.ddit.servlet05;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/ddit/fileProcess.do")
public class FileCommandProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		this.application = getServletContext();
	}
	private static enum CommandType{
		COPY((target, destFolder)->{
			// 복사
			String name = target.getName();
			File destFile = new File(destFolder, name);
			
			Files.copy(Paths.get(target.getPath()), Paths.get(destFile.getPath())
					,StandardCopyOption.REPLACE_EXISTING);
		}),
		DELETE((target, destFolder)->{
			// 삭제
			target.delete();
		}),
		MOVE((target, destFolder)->{
			// 이동 : 복사후 원본 삭제 ?
			COPY.fileProcess(target, destFolder);
			DELETE.fileProcess(target, destFolder);
		});
		
		private static interface CommandProcessor{
			// 람다식의 원형은 interface이기 때문에 interface에 예외처리를 해준다.
			public void process(File target, File destFolder) throws IOException;
		}
		private CommandProcessor processor;
		
		private CommandType(CommandProcessor processor) {
			this.processor = processor;
		}
		
		public void fileProcess(File target, File destFolder) throws IOException{
			processor.process(target, destFolder);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		// command : copy , move, delete
		String file = request.getParameter("file");
		String dest = request.getParameter("dest");
		
		int status = 200; 
		String msg = null; 
		CommandType commandType = null;
		
		try {
			commandType = CommandType.valueOf(command);
			
		}catch(Exception e) {
			status = 400; 
			msg = "처리할 수 있는 명령이 아닙니다.";
		}
		 
		if(status==200 && StringUtils.isBlank(file)) {
			status = 400; 
			msg = "대상 파일이 존재하지 않습니다. ";
		}
		
		File targetFile = null;
		if(status==200) {
			// servlet contextpath = real path
			targetFile = new File(application.getRealPath(file));
			if(!targetFile.exists() || targetFile.isDirectory()) {
				status=400;
				msg = "대상 파일이 파일형식이 아닙니다. ";
			}
		}
		File destFolder = null;
		if(status==200 && StringUtils.isNotBlank(dest)) {
			destFolder = new File(application.getRealPath(dest));
			if(!destFolder.exists() || destFolder.isFile()) {
				status=400;
				msg = "이동할 위치가 정상적이지 않습니다. ";
			}
		}
		if(status==200) {
			// 모든 검증에 통과 
			commandType.fileProcess(targetFile, destFolder);
		}
			// 마샬링, 직렬화
			Map<String, Object> resultMap = new LinkedHashMap<>();
			resultMap.put("status",status);
			resultMap.put("msg",msg);
			// 응답 : java >  json
			ObjectMapper mapper = new ObjectMapper();
			
			try(
				PrintWriter out = response.getWriter();
			){
				mapper.writeValue(out, resultMap);// 직렬화와 마샬링을 한번에 진행
			}
//		}else {	response.sendError(status, msg); }
	}
}
