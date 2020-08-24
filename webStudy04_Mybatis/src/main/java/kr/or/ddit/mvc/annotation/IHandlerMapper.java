package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

public interface IHandlerMapper {

	/**
	 * 특정요청을 처리할 수 있는 command handler를 검색 
	 * @param req
	 * @return
	 */
	public URIMappingInfo findCommandHandler(HttpServletRequest req);
	
}
