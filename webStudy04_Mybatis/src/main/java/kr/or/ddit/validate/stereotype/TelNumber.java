package kr.or.ddit.validate.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import kr.or.ddit.validate.TelNumberValidator;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {TelNumberValidator.class}) // single value
public @interface TelNumber {

	public String message() default "{kr.or.ddit.validate.stereotype.TelNumber.message}"; // 기본메세지 설정
	public Class<?>[] group() default {};
	public Class<? extends Payload>[] payload() default {};
}
