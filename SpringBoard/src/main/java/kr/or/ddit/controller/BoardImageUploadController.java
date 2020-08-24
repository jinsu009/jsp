package kr.or.ddit.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardImageUploadController {

	@Inject
	WebApplicationContext context;
	
	ServletContext application;
	
	@Value("#{appInfo.imagePath}") // el을 사용하기 위해 쓰는 annotation
	String imagePath; // 경로가 url 형태로 저장되어있다.
	
	@Value("#{appInfo.imagePath}") // 프로퍼티 에디터가 동작 , 파일의 명확한 주소가 저장된다.(realPath)
	File saveFolder; 
	
	@PostConstruct
	public void init() {
		application = context.getServletContext();
		// 폴더 유무 판단 후 없으면 새로 생성 
		// 저장위치는 변경될 수 있으므로 하드코딩 하지 않는다 > appInfo 이용
		if(!saveFolder.exists()) {
			saveFolder.mkdirs(); 
		}
	}
	
	// 이미지 업로드 처리
	@PostMapping(value = "/board/image", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> imageUpload(@RequestPart(required = true) MultipartFile upload) throws IllegalStateException, IOException {
		Map<String, Object> result = new HashMap<>();
		if(!upload.isEmpty()) {
			// uploade가 된다면
			// uploade 를  boardImages 라는 폴더에 저장한다.
			String savename = UUID.randomUUID().toString();
			upload.transferTo(new File(saveFolder, savename));
			
			// upload가 정상적으로 된 경우
			String fileName = upload.getOriginalFilename();
			int uploaded =1;
			String url =application.getContextPath() + imagePath + "/" + savename;
			
			result.put("fileName", fileName);
			result.put("uploaded", uploaded);
			result.put("url", url);
		}else {
//			error{number, message}
			Map<String, Object> error = new HashMap<>();
			String msg = "이미지가 없습니다.";
			int number = 400;
			error.put("number",number);
			error.put("msg",msg);
			
			result.put("error",error);
			
		}
		return result;
		
		
	}
}
