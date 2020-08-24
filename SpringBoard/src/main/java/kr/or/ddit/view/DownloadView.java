package kr.or.ddit.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.vo.AttatchVO;

public class DownloadView extends AbstractView{
	//AbstractView : 추상뷰 , 객체를 만들기 위해선 반드시 자식이 필요

	@Inject
	WebApplicationContext context;

	@Value("#appInfo.attatchPath")
	String attatchPath;
	
	
	// 다운로드 
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AttatchVO attatch = (AttatchVO) model.get("attatch");
		
		response.setHeader("Content-Length", attatch.getAtt_filesize()+"");
		// 파일명과 관련된 헤더 설정 
//		Content-Disposition: form-data; name="uploadFile"; filename="back_0.jpg"
		String filename = attatch.getAtt_filename();
		// 인코딩 : 현재 클라이언트가 어떤 브라우저를 사용하고 잇는지에 따라 결과가 다를 수 있고 다른방식을 적용해야한다. 
//		filename = URLEncoder.encode(filename,"UTF-8");
		// 어지간해서는 다 사용되는 코드 : 데이터를 1byte로 다 쪼개버린다.   
		filename = new String(filename.getBytes(),"ISO-8859-1");
		
		// 파일명에 공백이 포함되었을 경우 
		response.setHeader("Content-Disposition", "attatchment;filename=\""+filename+"\"");
		// inline : 갖고가자마자 사용
		
		String savename = attatch.getAtt_savename();
		Resource resource =  context.getResource(attatchPath+"/"+savename);
		try(
			OutputStream os = response.getOutputStream();
			InputStream is = resource.getInputStream();
		){
			IOUtils.copy(is, os);
		}
		
		
		
	}

}
