package kr.or.ddit.annotation.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SecondSingleValueAnnotation {

	public String value();
	// value라는 속성명은 생략이가능하다. 
}
