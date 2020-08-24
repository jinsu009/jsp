package kr.or.ddit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 모든 예외의 최상의 구조 : Throwable
 * 	- Error : 시스템 에러에 해당하며, 개발자가 처리하지 않음  
 *  - Exception : 개발자가 로직에 의해 처리할 수 있는 예외 
 *  	- checked Exception : 발생 가능성이 있는 경우, 반드시 처리해야할 예외
 *  			ex) IOException, SQLException
 *  	- unchecked Exception ( 상위 : Runtime Exception ) 
 *  			: 개발자가 처리하지 않더라도 , 호출자에게 제어권이 전달되는 예외
 *  			ex) NullPointerException, IllegalArgumentException
 *
 * 처리 방법 
 * 	1) 적극 처리 : try~catch~finally - 예외 발생 메소드 내에서 직접 처리  
 *  2) 수동 처리 : throws - 예외 발생 메소드의 호출자에게 처리를 위임
 *  
 *  커스텀 예외 정의 : 예외의 처리 제약에 따라 상위 만 선택하면 된다.
 *  예외 발생시 사용 : throw new 예외 객체 생성  
 *
 */


// 2020-05-20
//2020-06-11
//2020-06-17
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException{

	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
}
