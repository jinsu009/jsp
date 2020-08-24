package kr.or.ddit.mvc.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParameter {

	public String name();
	
	public boolean required() default true; // 만약 required가 없으면 필수 파라미터 이다. (?)
	
	public String defaultValue() default "";
}
