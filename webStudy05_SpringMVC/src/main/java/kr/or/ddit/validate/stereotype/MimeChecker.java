package kr.or.ddit.validate.stereotype;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import kr.or.ddit.validate.MimeCheckerValidator;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target({FIELD, METHOD}) // java bean 규약에 따라 모두 캡슐화 시켜놨음 
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MimeCheckerValidator.class})
public @interface MimeChecker {

	public String contentType(); // default가 없으니까 필수적으로 값을 설정해줘야한다. 
	
	public String message() default "{kr.or.ddit.validate.stereotype.MimeChecker.message}";
	public Class<?>[] groups() default{}; //하나의 annotation안에 여러개의 group를 설정할 수 있다.
	public Class<? extends Payload>[] payload() default{};

	
}
