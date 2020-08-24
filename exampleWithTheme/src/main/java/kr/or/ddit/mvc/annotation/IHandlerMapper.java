package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

public interface IHandlerMapper {
	/**
	 * 특정 요청을 처리할 수 있는 command handler 를 검색.
	 * @param req
	 * @return
	 */
	public URIMappingInfo findCommandHandler(HttpServletRequest req);
}
