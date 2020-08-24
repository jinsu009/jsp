package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.commons.dao.IResourceDAO;
import kr.or.ddit.commons.dao.ResourceDAOImpl;
import kr.or.ddit.mvc.annotation.HandlerMapper;
import kr.or.ddit.vo.ResourceVO;

// 2020-06-04
/**
 * 보호 자원에 대한 요청에 대해 현재 요청을 발생시킨 클라이언트가 인증된 클라이언트인지 확인 
 * 인증된 클라이언트 : 자원 제공 
 * 아직 인증되지 않은 클라이언트 : 로그인 폼 으로 이동 
 * 보호가 필요한 자원들의 명세가 필요하다. : mypage, delete , .. 
 * /webStudy03_MVCFramework/src/main/resources/kr/or/ddit/securedResources.properties
 */
public class AuthenticationFilter implements Filter{

	private Map<String, Set<String>> securedResources;
	private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	private IResourceDAO resourceDao = new ResourceDAOImpl();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		securedResources = new HashMap<>();
		filterConfig.getServletContext().setAttribute("securedResources", securedResources);
		// properties 정보 가져오는 방법 : 객체 사용 or resource bundle 사용
		//-----
		// 데이터 베이스로부터 조회 후 Map 완성 
		List<ResourceVO> resourceList = resourceDao.selectResourceList();
		for(ResourceVO res : resourceList) {
			securedResources.put(res.getRes_url(), res.getRoles());
		}
		System.err.println(securedResources);
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 현재 요청이 보호자원에 대한 요청인지 판단
		// 보호자원 요청인 경우 : 보호자원을 사용할수 있는 상황인지 판단 
		// 인증된 유저 : 통과 
		// 인증되지 않은 유저 : 인증 페이지로 이동 
		// 보호자원 요청이 아닌경우 : 통과 
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = HandlerMapper.parseRequestURI(req);
		boolean pass = true;
		if(securedResources.containsKey(uri)) {
			Object authUser = req.getSession().getAttribute("authUser");
			if(authUser == null) {
				pass = false;
			}
		}
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendRedirect(req.getContextPath()+"/login/loginForm.jsp");
		}
	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
