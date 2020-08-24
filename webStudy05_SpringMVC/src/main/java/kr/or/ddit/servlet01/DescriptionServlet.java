package kr.or.ddit.servlet01;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 운영과정에서 컨테이너의 역할 
 * container : 컨테이너에 의해 관리되는 객체의 라이프사이클 제어자 
 * Web Container == Servlet Container == JSP Container == WAS(Web Application Server) -- tomcat
 * 톰캣의 역할 
 * 1. Servlet의 객체 생성 : 해당 서블릿을 대상으로 최초의 요청이 발생(load-on-startup 으로 생성시점 제어 가능)
 * 		생성된 서블릿은 기본 싱글톤으로 관리 
 * 		- init 
 * 2. 매핑된 요청이 발생하면, 관련된 콜백 메소드 호출 
 * 		- service, doXXX 
 * 
 * 3. 필요없어지면, GC의 대상으로 설정 
 * 		- destroy
 * 
 */

// 아무의미없는 주석 > 서블릿이 소멸됨을 보기 위해 만들었다. 

public class DescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DescriptionServlet() {
       super();
       System.out.println(getClass().getSimpleName()+" 서블릿 객체 생성");
    }
    @Override
    	public void init(ServletConfig config) throws ServletException {
    		// init 에서는 절대 지우면 안됨 
			// 라이플 사이클 이내에 한번만 호출되는 메소드
    		super.init(config); 
    		String param = config.getInitParameter("test"); // 파라미터 명을 작성해줘야하는데
    		Enumeration<String> names = config.getInitParameterNames(); // 파라미터의 이름을 모를때 사용
    		// iterator 와 출력문이 같은 형식 
    		while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				String value = config.getInitParameter(name);
				System.out.printf("%s : %s" , name, value);
			}
    		
    		System.out.println(getClass().getSimpleName()+ " 서블릿 초기화 , 전달 파라미터 " + param );
    	}
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("service 메소드의 첫 라인");
//    	super.service(req, resp); //doget메소드 호출 
    	System.out.println("service 메소드의 마지막 라인");
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(Thread.currentThread().getName()); 
		// 현재 실행되는 스레드이름 
		// 요청이 들어올때마다 다른 스레드가 실행되는 것을 알수있따. 
		// 반복적인 스레드 사용 ( 스레드가 재활용되고 잇따.)
		// =====
		// 톰캣은 최초의 요청이 들어오면 객체가 생성되고 기본적으로 싱글톤이 사용되므로 
		// 객체는 한번만 생성되고 스레드가 반복적으로 사용되고 잇따. (재활용)
		// ====
		// .xml 에서 객체 생성 순서를 바꿔줄수있다. 
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	@Override
	public void destroy() {
		// 라이플 사이클 이내에 한번만 호출되는 메소드
		// 서블릿이 필요없는 상황은 명시적으로 정해줄수 없다. 
		// 그래서 메모리가 부족하게 되면 소멸시키는 메소드가 실행된다. 
		// destroy는 실행이 될수도 있고 안될수도 있다. 
		super.destroy();
		System.out.println(getClass().getSimpleName()+" 서블릿 소멸");
	}
}
