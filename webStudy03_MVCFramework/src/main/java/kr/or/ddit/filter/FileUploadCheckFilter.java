package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;

//파일이 업로드 됐는지 확인
// 2020-06-03
/**
 * Filter : 요청과 응답에 대한 전/후 처리를 담당하는 객체 , 데이터를 변환하거나, 흐름을 제어해서 파일에 대한 처리나 보안 처리등에 활용
 * 
 * Filter의 구현단계 ( Decorating Filter Pattern )
 * 	1. javax.servlet.Filter 의 구현 ( callback 메소드 구현 )
 *  2. doFilter 내에서 실제 요청과 응답 필터링 
 *  	1) 요청 필터링 
 *  	2) chain.doFilter ( 이 순서 가 없으면 흐름이 끊긴다. 제일 중요한 부분 )
 *  	3) 응답 필터링 
 * 	3. WAS에게 필터 등록 (web.xml) 
 * 		*주의 : WAS는 등록된 필터로 filterchain을 구성한다. - filterchain 내에서 필터링 되는 순서는 필터를 등록할 때 순서에 따른다. 
 * 	4. 매핑 설정으로 필터링할 요청을 결정
 * 
 *  prodinsert , produpdate에서 중복된 코드를 줄이기 위해 만든 클래스 
 *  
 *  **** 현재 클래스에서 확인해야할 것 
 *  1. 현재 요청이 multipart request인지 여부를 확인
 *  2. multipart request 라면, request wrapper 구조를 활용 
 */

public class FileUploadCheckFilter implements Filter{

	private static Logger logger = LoggerFactory.getLogger(FileUploadCheckFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 필터 초기화 ", this.getClass().getSimpleName());
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// wrapper : 감쌀수 있는 원본이 필요하다, wrapping한 모든 객체의 메소드를 사용할 수 있다. 
		HttpServletRequest req = (HttpServletRequest) request;
		// form 태그 post method로 req를 받아야한다. -- body가 없으면 null 반환 (get method)
		String contentType = req.getContentType();
		if(contentType!=null && contentType.startsWith("multipart/")) { 
			FileUploadRequestWrapper wrapper = new FileUploadRequestWrapper(req);
			chain.doFilter(wrapper, response);
		}else {
			// 원본 요청과 응답을 그대로 전송해준다. 
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		logger.info("{} 필터 소멸 ", this.getClass().getSimpleName());
	}
	
}
