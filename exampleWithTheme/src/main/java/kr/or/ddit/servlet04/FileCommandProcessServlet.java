package kr.or.ddit.servlet04;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.processing.Processor;
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
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.application = getServletContext();
	}
	private static final long serialVersionUID = 1L;
	private ServletContext application;
	
	private static enum CommandType{
		COPY((target, destFolder)->{
			String name = target.getName();
			File destFile = new File(destFolder, name);
			Files.copy(Paths.get(target.getPath()), 
					   Paths.get(destFile.getPath()), 
					   StandardCopyOption.REPLACE_EXISTING);
		}),
		DELETE((target, destFolder)->{
			target.delete();
		}),
		MOVE((target, destFolder)->{
			COPY.fileProcess(target, destFolder);
			DELETE.fileProcess(target, destFolder);
		});
		private static interface CommandProcessor{
			public void process(File target, File destFolder) throws IOException;
		}
		private CommandProcessor processor;
		private CommandType(CommandProcessor processor) {
			this.processor = processor;
		}
		
		public void fileProcess(File target, File destFolder) throws IOException {
			processor.process(target, destFolder);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		String file = request.getParameter("file");
		String dest = request.getParameter("dest");
		int status = 200;
		String message = null;
		CommandType commandType = null;
		try {
			commandType = CommandType.valueOf(command);
		}catch (Exception e) {
			status = 400;		
			message = "처리할수 있는 명령이 아님.";
		}
		if(status==200 && StringUtils.isBlank(file)) {
			status = 400;
			message = "대상 파일이 존재하지 않음.";
		}
		File targetFile = null;
		if(status==200) {
			targetFile = new File(application.getRealPath(file));
			if(!targetFile.exists() || targetFile.isDirectory()) {
				status = 400;
				message = "대상 파일이 파일이 아님.";
			}
		}
		File destFolder = null;
		if(status==200 && StringUtils.isNotBlank(dest)) {
			destFolder = new File(application.getRealPath(dest));
			if(!destFolder.exists() || destFolder.isFile()) {
				status = 400;
				message = "이동할 위치가 정상적이지 않음.";
			}
		}
		if(status==200) {
			commandType.fileProcess(targetFile, destFolder);
		}	
		Map<String, Object> resultMap = new LinkedHashMap<>();
		resultMap.put("status", status);
		resultMap.put("message", message);
		ObjectMapper mapper = new ObjectMapper();
		try(
			PrintWriter out = response.getWriter();
		){
			mapper.writeValue(out, resultMap);
		}
	}
}





















