package kr.or.ddit.mvc;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mvc.annotation.HandlerInvoker;
import kr.or.ddit.mvc.annotation.HandlerMapper;
import kr.or.ddit.mvc.annotation.IHandlerInvoker;
import kr.or.ddit.mvc.annotation.IHandlerMapper;
import kr.or.ddit.mvc.annotation.URIMappingInfo;

/**
 * 2020-05-29 Front Controller Pattern 을 적용하여 각 컨트롤러에서 반복되는 코드를 제거하기 위함 
 * FrontController 로서 모든 요청의 entry point가 되며,
 * 각 commend controller들의 invoker역할을 한다.
 */

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(FrontController.class);
	
	IHandlerMapper handlerMapper;
	IHandlerInvoker handlerInvoker;
	IViewProcessor viewProcessor;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String basePackage = config.getInitParameter("basePackage");
		handlerMapper = new HandlerMapper(basePackage);
		handlerInvoker = new HandlerInvoker();
		
		String prefix = config.getInitParameter("prefix");
		String suffix = config.getInitParameter("suffix");
		
		viewProcessor = new ViewProcessor();
		viewProcessor.setPrefix(Objects.toString(prefix,""));
		viewProcessor.setSuffix(Objects.toString(suffix,""));
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		URIMappingInfo mappingInfo = handlerMapper.findCommandHandler(req);
		
		if(mappingInfo==null) {
			resp.sendError(404,"해당 서비스는 지원하지않아");
			return;
		}
		
		String viewName = handlerInvoker.invokeHandler(mappingInfo, req, resp);
		
		if(viewName==null) {
			if(!resp.isCommitted()) {
				resp.sendError(500,"논리적인 뷰네임 문제");
			}
			return;
		}
		viewProcessor.viewProcess(viewName, req, resp);
		
	}
}
