package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.mvc.stereotype.ModelData;
import kr.or.ddit.mvc.stereotype.RequestParameter;

public class HandlerInvoker implements IHandlerInvoker {

	@Override
	public String invokeHandler(URIMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
	
		Object controller = mappingInfo.getCommandHandler(); // memberlistcontroller
		Method handlerMethod = mappingInfo.getHandlerMethod(); // list
		
		Parameter[] parameters = handlerMethod.getParameters();
		try {
			if(parameters.length==0) {
				return (String) handlerMethod.invoke(controller);
			}else {
				Object[] realParamters = new Object[parameters.length];
				
				for(int i=0; i<parameters.length; i++) {
					Parameter param = parameters[i];
					Class<?> paramType = param.getType();
					ModelData modelData = param.getAnnotation(ModelData.class);
					
					RequestParameter requestParameter = 
							param.getAnnotation(RequestParameter.class);
					
					if(paramType.equals(HttpServletRequest.class)) {
						// 현재 파라미터 타입이 리퀘스트를 받는 타입인지 확인 
						realParamters[i] = req;
					}else if(paramType.equals(HttpServletResponse.class)) {
						realParamters[i] = resp;
					}else if(modelData!=null) {
						realParamters[i] = makeModel(req, paramType, modelData);
					}else if(requestParameter!=null) {
						// request 에서 하나의 데이터를 꺼내야 한다. 
						realParamters[i] = generateParameter(req, paramType, requestParameter);
					}else {
						throw new RuntimeException("프레임워크가 예상황 상황이 아니에여 ");
					}
				}
				
				return (String) handlerMethod.invoke(controller, realParamters);
			}
		}catch (IllegalArgumentException e) {
			resp.sendError(400, e.getMessage());
			return null;
		}
		catch (Exception e ) {
			throw new ServletException(e);
		}
	}

	private Object generateParameter(HttpServletRequest req, Class<?> paramType, RequestParameter requestParameter) {
		String name = requestParameter.name();
		String paramValue = req.getParameter(name);
		if(requestParameter.required() && (paramValue==null || paramValue.isEmpty())) {
			// 필수 데이터가 없으니까 400에러 발생 
			throw new IllegalArgumentException(name+" 에 해당하는 요청 파라미터 누락");
		}else {
			// 필수데이터가 아니거나 값이 있는 데이터 
			// int 형으로 casting 할것인지 string으로 갈것인지 고려 
			
			Object realparameter = null;
			if(paramValue==null || paramValue.isEmpty()) {
				// 필수파라미터가 아니라서 값이 넘어오지 않았더라도 defaultvalue값을 넘겨준다. 
				paramValue = requestParameter.defaultValue();
			}
			
			if(String.class.equals(paramType)) {
				realparameter = paramValue;
			}else if(byte.class.equals(paramType) || Byte.class.equals(paramType)) {
				realparameter = Byte.parseByte(paramValue);
			}else if(short.class.equals(paramType) || Short.class.equals(paramType)) {
				realparameter = Short.parseShort(paramValue);
			}else if(int.class.equals(paramType) || Integer.class.equals(paramType)) {
				realparameter = Integer.parseInt(paramValue);
			}else if(long.class.equals(paramType) || Long.class.equals(paramType)) {
				realparameter = Long.parseLong(paramValue);
			}else if(float.class.equals(paramType) || Float.class.equals(paramType)) {
				realparameter = Float.parseFloat(paramValue);
			}else if(double.class.equals(paramType) || Double.class.equals(paramType)) {
				realparameter = Double.parseDouble(paramValue);
			}else if(char.class.equals(paramType) || Character.class.equals(paramType)) {
				realparameter = paramValue.substring(0,1); //return String
//				realparameter = paramValue.charAt(0); //return char
			}else if(boolean.class.equals(paramType) || Boolean.class.equals(paramType)) {
				realparameter = Boolean.parseBoolean(paramValue);
			}else {
				// 기본형이 아닌것에 사용됐을 때 : 개발자가 framework을 잘못사용한 경우 
				throw new RuntimeException("@RequestParameter는 기본형만 사용가능 ");
			}
			return realparameter;
		}
	}

	private Object makeModel(HttpServletRequest req, Class<?> paramType, ModelData modelData) {
		try {
			Object realParam = paramType.newInstance();
			req.setAttribute(modelData.value(), realParam);
			BeanUtils.populate(realParam, req.getParameterMap());
			return realParam;
		} catch (Exception e) {
			// realparam 이 javabean 규약에 맞춰지지 않음 
			// 넘겨진 데이터의 타입이 vo 타입에 맞지 않음 
			throw new RuntimeException(e);
		}
	}
}
