package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class URIMappingInfo {
	private URIMappingCondition mappingCondition;
	private Object commandHandler;
	private Method handlerMethod;
	
	public String toString() {
		return commandHandler.getClass().getName()+"."+handlerMethod.getName();
	}
}









