package kr.or.ddit.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

/**
 * Application Lifecycle Listener implementation class EventListener
 *
 */
@WebListener
public class TotalEventListener implements ServletContextListener, HttpSessionListener, ServletRequestListener, HttpSessionAttributeListener{
	
	private Logger logger = LoggerFactory.getLogger(TotalEventListener.class);
	
	// 네이버 카페에 들어가면 접속자 목록을 체크 
	// 회원들의 목록을 나타내는 collection 생성 -- membervo에서 정보 받아오기  
	// 
	
	
	// 1. context lifeCycle : ServletContextListener
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// context 생성시 처리되는 이벤트 server start 
		logger.info("{} context start", sce.getServletContext().getContextPath());
		ServletContext application = sce.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		
		// 어플리케이션 시작시 collection 생성 
		application.setAttribute("userList", new HashSet<MemberVO>());
		// 같은 member는 한번만 넣겠다. : member equals hashcode
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// server stop
		// 가비지 컬렉션 이 언제 종료되느냐에 따라 logger가 안나올수도 나올수도 있다.
		logger.info("{} context stop", sce.getServletContext().getContextPath());
		
	}
	
	// 2. session lifecycle : HttpSessionListener , 쿠키종료, 로그아웃 , session 만료 시간 경과
	// 현재 사이트에 접속자 수 검사 
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.info("{} session create", se.getSession().getId());
		
		
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("{} session delete", se.getSession().getId());
		
	}
	
	// 3. request lifecycle : ServletRequestListener

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// 어느시간대에 어느지역에서 클라이언트의 요청이 많이 발생하는지 통계를 측정할 때
//		logger.info("{}로 부터 {} 요청 발생. ", sre.getServletRequest().getRemoteAddr(), new Date());
//		long startTime = System.currentTimeMillis();
//		sre.getServletRequest().setAttribute("startTime", startTime);
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// 응답데이터가 나가는 시간 , 소요시간을 체크 (start time : requestInitialized, end time)
//		long startTime = (Long) sre.getServletRequest().getAttribute("startTime");
//		long endTime = System.currentTimeMillis();
//		logger.info("{}요청의 소요시간  {} ms. ", sre.getServletRequest().getRemoteAddr(), (endTime-startTime));
	}

	//4. application scope : ServletContextAttributeListener
	// 5. session scope : HttpSessionAttributeListener **
	// 6. request scope : 
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// 누군가 로그인 성공했을 때 
		String attrName = event.getName();
		if("authUser".equals(attrName)) {
			MemberVO authUser = (MemberVO) event.getValue();
			logger.info("누가 로그인 했는지 확인해보자 : {}", authUser.getMem_name());
			Set<MemberVO> userList = (Set<MemberVO>) event.getSession().getServletContext().getAttribute("userList");
			userList.add(authUser);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// 로그아웃 했을 때 
		String attrName = event.getName();
		if("authUser".equals(attrName)) {
			MemberVO authUser = (MemberVO) event.getValue();
			logger.info("누가 로그아웃 했는지 확인해보자 : {}", authUser.getMem_name());
			Set<MemberVO> userList = (Set<MemberVO>) event.getSession().getServletContext().getAttribute("userList");
			userList.remove(authUser);	
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// 값을 바꿀 때 실행 
		
	}

}
