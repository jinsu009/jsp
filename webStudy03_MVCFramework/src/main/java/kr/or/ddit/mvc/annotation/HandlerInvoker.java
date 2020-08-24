package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerInvoker implements IHandlerInvoker {

	@Override
	public String invokeHandler(URIMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
	
		Object controller = mappingInfo.getCommandHandler(); // memberlistcontroller
		Method handlerMethod = mappingInfo.getHandlerMethod(); // list
		try {
			return (String) handlerMethod.invoke(controller, req, resp);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
