package kr.or.ddit.mvc.annotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IHandlerInvoker {
	
	/**
	 * POJO 형태로 구현된 command handler를 호출하는 역할 
	 * @param mappingIngo
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String invokeHandler(URIMappingInfo mappingInfo, HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException;
}
