package kr.or.ddit.mvc.annotation;

import kr.or.ddit.mvc.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 
 * urimapping이 가진 데이터를 캡슐화한 객체
 * 어떤 주소, 어떤 메소드의 요청인지를 식별할 때 사용됨
 */

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class URIMappingCondition {

	private String uri;
	private HttpMethod method;
	// 두개의 값을 하나의 객체로 만들기 위해 > 캡슐화
}
