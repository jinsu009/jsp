package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;

public class SampleFilter implements Filter{

	private Logger logger = LoggerFactory.getLogger(SampleFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 필터 초기화", this.getClass().getSimpleName());		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		logger.info("wrapper 인지 확인!! {}", req instanceof FileUploadRequestWrapper);
		
		logger.info("요청 uri : {}", req.getRequestURI());
		
		// 요청 필터링
		chain.doFilter(request, response);
		
		//응답 필터링
		logger.info("응답 크기 : {}" , resp.getHeader("Content-Length"));
	}

	@Override
	public void destroy() {
		logger.info("{} 필터 소멸", this.getClass().getSimpleName());		
	}

	
	
}
