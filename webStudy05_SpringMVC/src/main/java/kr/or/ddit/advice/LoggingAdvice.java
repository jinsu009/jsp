package kr.or.ddit.advice;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 2020-06-15
@Aspect
public class LoggingAdvice {
	// aspect = advice + pointcut
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// 중복코드 제거 
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {}
	
	// pointcut
	@Around("dummy()")
	public Object arround(ProceedingJoinPoint joinpoint) throws Throwable {
		String clzName = joinpoint.getTarget().getClass().getSimpleName(); // 핵심관심사
		Signature signature = joinpoint.getSignature();
		String methodName = signature.getName();
		Object[] args = joinpoint.getArgs();
		logger.info("{}.{} 호출, 전달 파라미터 : {}",clzName, methodName, Arrays.toString(args));
		long startTime = System.currentTimeMillis();
		Object retValue = joinpoint.proceed(args);
		long endTime = System.currentTimeMillis();
		logger.info("{}.{} 호출 종료  [소요시간 {}ms] , 반환값 : {}",clzName, methodName, endTime-startTime, retValue);
		return retValue;
	}
	
	@Before("dummy()")
	public void before() {
		logger.info("핵심 관심사 (target) 호출 ");
	}
}
