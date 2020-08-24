package kr.or.ddit.mvc.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import kr.or.ddit.mvc.HttpMethod;


/**
 * command handler를 가진 핸들러 객체의 실제 핸들러 메소드를 식별하기위한 기준
 * 요청, 즉 커맨드와의 매핑 설정을 위해 value와 method속성을 사용  
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface URIMapping {

	public String value();
	
	public HttpMethod method() default HttpMethod.GET;
	
}
