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
		// 어느곳에 존재하는 어떤 메소드인지 정보를 return
		return commandHandler.getClass().getName()+"."+handlerMethod.getName();
	}
	
}
