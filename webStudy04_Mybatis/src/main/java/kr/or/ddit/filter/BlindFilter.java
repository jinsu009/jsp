package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 클라이언트를 블라인드 처리해서 특정서비스를 이용하지 못하도록 한다. 
 *  -- 연습용 
 *
 */
public class BlindFilter implements Filter{
	
	private Map<String, String> blackList; // IP, 블라인드처리가 된 이유
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		blackList = new HashMap<>();
		blackList.put("127.0.0.1","그으냥");
		blackList.put("192.168.0.111","그으냥");
		blackList.put("0:0:0:0:0:0:0:1","그으으냥"); 
		// ip address loopback 방식 
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 들어오는 모든 요청 확인 
		// 1. 클라이언트의 ip 확보 
		// 2. 블랙리스트 대상이 아닐경우 : 통과 
		// 3. 블랙리스트 대상일 경우 : 통과가 되지않는 사유를 보여준다. > messageView이동, 사유를 전달
		String clientIP = request.getRemoteAddr();
		System.out.println(clientIP);
			
		if(blackList.containsKey(clientIP)) {
			String reason = blackList.get(clientIP);
			request.setAttribute("reason", reason);
			request.getRequestDispatcher("/WEB-INF/views/messageView.jsp").forward(request, response);
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
