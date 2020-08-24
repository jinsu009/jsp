package kr.or.ddit.mvc.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.utils.ReflectionUtils;

public class HandlerMapper implements IHandlerMapper {
	
	private Map<URIMappingCondition, URIMappingInfo> handlerMap;
	private static Logger logger = LoggerFactory.getLogger(HandlerMapper.class);
	
	public HandlerMapper(String...basePackages) {
		handlerMap = new LinkedHashMap<>();
		Class<? extends Annotation> annotationType = CommandHandler.class;
		Map<Class<?>, Annotation> handlerClasses =
				ReflectionUtils.getClassesWithAnnotationAtBasePackages(annotationType, basePackages);
		Iterator<Class<?>> it = handlerClasses.keySet().iterator();
		annotationType = URIMapping.class;
		while (it.hasNext()) {
			Class<?> handlerClass = (Class<?>) it.next();
			Object handlerObj = null;
			try {
				handlerObj= handlerClass.newInstance();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				continue;
			}
			Map<Method, Annotation> methodMap = 
					ReflectionUtils.getMethodsWithAnnotationAtClass(handlerClass, annotationType, 
					String.class, HttpServletRequest.class, HttpServletResponse.class);
			for(Entry<Method, Annotation> entry : methodMap.entrySet()) {
				Method handlerMethod = entry.getKey();
				URIMapping mapping = (URIMapping) entry.getValue();
				String uri = mapping.value();
				HttpMethod method = mapping.method();
				URIMappingCondition condition = new URIMappingCondition(uri, method);
				URIMappingInfo mappingInfo = new URIMappingInfo(condition, handlerObj, handlerMethod);
				handlerMap.put(condition, mappingInfo);
				logger.info("{} : {}", condition, mappingInfo);
			}
		}
	}

	@Override
	public URIMappingInfo findCommandHandler(HttpServletRequest req) {
		String uri = req.getRequestURI().split(";")[0];
		int length = req.getContextPath().length();
		uri = uri.substring(length);
		HttpMethod method = HttpMethod.valueOf(req.getMethod().toUpperCase());
		URIMappingCondition condition = new URIMappingCondition(uri, method);
		return handlerMap.get(condition);
	}

}





