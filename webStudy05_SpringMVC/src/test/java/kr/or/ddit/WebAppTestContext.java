package kr.or.ddit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ContextHierarchy({
	@ContextConfiguration(locations = "file:webapp/WEB-INF/spring/*-context.xml") // single value element
	,@ContextConfiguration(locations = "file:webapp/WEB-INF/spring/appServlet/servlet-context.xml")
})
@WebAppConfiguration
@Transactional // 모든 test case는 자동 rollback
public @interface WebAppTestContext {

	// 0616 
	/**
	 * 복합 annotation
	 * runwith는 복합 anno를 허용하지 않는다. 
	 * 
	 *  현재 annotation은 상위 context만 등록되어 있다.
	 *  ContextHierarchy 를 이용해 대칭구조 형성 
	 */
}
