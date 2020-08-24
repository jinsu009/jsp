package kr.or.ddit.filter.wrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.Part;

import lombok.Getter;

@Getter
public class PartWrapper {

	private Part part;

	public PartWrapper(Part part) {
		super();
		this.part = part;
		this.contentType = part.getContentType();
		this.filesize = part.getSize();
		this.partName = part.getName();
		this.originalFilename = parseFileName(part);
		this.savename = UUID.randomUUID().toString();
	}
	
	
	private String parseFileName(Part part) {
		String header = part.getHeader("Content-Disposition");
		int index = header.indexOf("filename");
		if(index == -1) {
			// 파일을 선택하지 않은 상태로 submit : 업로드가 안되고있음
			return null;
		}
		int secondIndex = header.indexOf("=", index);
		header = header.substring(secondIndex+1);
		return header.replace("\"", "");
	}


	private String contentType;
	private long filesize;
	private String partName;
	private String originalFilename;
	private String savename; 
	// 이름이 똑같게 되면 저장파일중 하나는 사라져야한다 - 유일한 식별자 사용 : 현재시간 사용 , 객체 사용
	
	public void saveFile(File saveFolder) throws IOException {
		
		if(originalFilename==null || originalFilename.isEmpty()) {
			return;
		}
		
		//원본파일명과 식별자(확장자)가 사라짐
		File saveFile = new File(saveFolder, savename);
		try(
				// 이진데이터 복사 
				InputStream is = part.getInputStream(); // 입력 스트림
				FileOutputStream fos = new FileOutputStream(saveFile);
			){
				// 복사
				byte[] buffer = new byte[1024];
				int pointer = -1;
				while((pointer = is.read(buffer))!=-1) {
					fos.write(buffer,0, pointer);
				}
			}
	}
	
}
