package kr.or.ddit.validate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import kr.or.ddit.vo.MemberVO;

/**
 * Domain Layer 에 대한 검증 지원
 * 
 * 
 *
 */
// 2020-06-10
public class CommonValidator<T> {
	Validator validator;
	
	// 초기화 코드블럭 : 생성자 
	{
		Validation.byDefaultProvider()
			.configure().messageInterpolator(new ResourceBundleMessageInterpolator(
				new PlatformResourceBundleLocator("kr.or.ddit.validate.ValidationMessage")
			)).buildValidatorFactory().getValidator();
//		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	public Map<String, List<String>> validate(T validateTarget, Class<?>...groups) {
		Set<ConstraintViolation<T>> violations = validator.validate(validateTarget,groups);
		System.out.println(violations);
		Map<String, List<String>> errors = new LinkedHashMap<>();
		for(ConstraintViolation<T> violation:violations) {
			String key = violation.getPropertyPath().toString();
			String msg = violation.getMessage();
			System.out.printf("%s : %s \n",key,msg);
			List<String> already = errors.get(key);
			if(already==null) {
				already = new ArrayList<>();
				errors.put(key, already);
			}
			already.add(msg); // 메세지는 계속 list로 들어가 있다. 
		}
		return errors;
	}
}
