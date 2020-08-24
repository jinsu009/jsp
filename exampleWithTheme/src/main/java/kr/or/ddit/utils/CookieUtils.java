package kr.or.ddit.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 쿠키 객체 생성과 쿠키 객체의 획득을 지원하는 클래스
 * DI (Dependency Injection) : 주입자(container) 필요.
 * 1. Setter Injection
 * 2. Constructor Injection
 * DI 구조를 활용한 Strategy Pattern : 객체의 운용 환경에 따라 다른 전략 객체를 주입받아 사용하는 패턴. 
 *
 */
public class CookieUtils {
	private HttpServletRequest request;
	private Map<String, Cookie> cookieMap;
	
	public CookieUtils(HttpServletRequest request) {
		super();
		this.request = request;
		cookieMap = new LinkedHashMap<>();
		generateMap(request);
	}

	private void generateMap(HttpServletRequest request2) {
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie tmp : cookies) {
				String name = tmp.getName();
				cookieMap.put(name, tmp);
			}
		}
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
		cookieMap.clear();
		generateMap(request);
	}
	
	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}
	
	public String getCookieValue(String name) throws UnsupportedEncodingException{
		Cookie cookie = cookieMap.get(name);
		String value = null;
		if(cookie!=null) {
			value = URLDecoder.decode(cookie.getValue(), "UTF-8");
		}
		return value;
	}
	
	public boolean exists(String name){
		return cookieMap.containsKey(name);
	}
	
	public Map<String, Cookie> getCookieMap() {
		return cookieMap;
	}
	
	/**
	 *  쿠키 생성
	 * @param name 쿠키명
	 * @param value 인코딩될 쿠키값
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value) throws IOException{
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
		return cookie;
	}
	
	/**
	 * 쿠키 생성
	 * @param name
	 * @param value
	 * @param maxAge 초단위 만료시간
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, int maxAge) throws IOException{
		Cookie cookie = createCookie(name, value);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
//	public static final boolean DOMAIN = true;
//	public static final boolean PATH = false;
	public static enum TextType{DOMAIN, PATH}
	
	/**
	 * 쿠키 생성
	 * @param name
	 * @param value
	 * @param text path 나 domain 으로 사용할 텍스트
	 * @param type text 파라미터를 path 혹은 domain 에 사용할지 여부를 결정할 식별자
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, String text, TextType type) throws IOException {
		Cookie cookie = createCookie(name, value);
		if(TextType.PATH.equals(type)) {
			cookie.setPath(text);
		}else if(TextType.DOMAIN.equals(type)){
			cookie.setDomain(text);
		}
		return cookie;
	}
	
	/**
	 * @param name
	 * @param value
	 * @param text
	 * @param type
	 * @param maxAge 초단위 만료 시간
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, 
					String text, TextType type, int maxAge) throws IOException {
		Cookie cookie = createCookie(name, value, text, type);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	/**
	 * @param name
	 * @param value
	 * @param domain 도메인
	 * @param path 경로
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, 
			String domain, String path) throws IOException {
		Cookie cookie = createCookie(name, value);
		cookie.setDomain(domain);
		cookie.setPath(path);
		return cookie;
	}
	
	/**
	 * 쿠키의 대부분 속성을 설정한 메소드
	 * @param name
	 * @param value
	 * @param domain
	 * @param path
	 * @param maxAge
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, 
			String domain, String path, int maxAge) throws IOException {
		Cookie cookie = createCookie(name, value, domain, path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	
}










