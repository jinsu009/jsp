package kr.or.ddit.filter.auth;

import java.io.IOException;
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

import kr.or.ddit.mvc.annotation.HandlerMapper;
import kr.or.ddit.vo.MemberVO;

/**
 * 인증된 클라이언트에 대해 현재 요청하고 있는 보호 자원에 대한 권한을 소유했는지 여부를 확인.
 * 허가받은 자원에 대해 요청을 하고 있는지 확인 : 인가
 * 인증 > 인가 > 
 * 소유하고 있는 지 확인 클라이언트가 role를 가지고 있어야 한다. 
 */
public class AuthorizationFilter implements Filter{
	
	private Map<String, Set<String>> securedResources ;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// map 가져오기 
		System.out.println("\n 두번재 필터@!!!! \n");
		if(securedResources == null) {
			securedResources = (Map<String, Set<String>>) request.getServletContext().getAttribute("securedResources");
		}
		System.out.println("디비 크기!! : " +securedResources.size());
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = HandlerMapper.parseRequestURI(req);	// 현재 자원에 접근할수 있는 권한이 무엇인지 가져와야한다. 
		
		Set<String> roles = securedResources.get(uri);
		boolean pass = true;
		if(roles!=null) {
			// 현재유저의 정보 : 권한을 가져온다. 
			MemberVO authUser = (MemberVO) req.getSession().getAttribute("authUser");
			Set<String> mem_roles = authUser.getMem_roles();
			boolean authorized = false;
			for(String role : mem_roles) {
				authorized = mem_roles.contains(role);
				if(authorized) { break;	} 	// 권한을 갖고 있다.
			}
			pass = pass && authorized; // 둘 만족해야지만 통과 
		}
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, uri+"는 접근 할 수 없는 자원입니다.");
		}
		
	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}
