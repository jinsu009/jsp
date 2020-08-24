package kr.or.ddit.util;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

public class LocaleTestCase {
	
	// POJO : Plain Old Java Object 
	// test 메소드 명을 별의미없는 단어로 변경해도 별로 상관이 없다. 
	// 얽메어져 있는 메소드가 없으므로 아무렇게나 작성해도된다. 
	
	@Test
	public void test1() {
		Locale defaultLoc = Locale.getDefault();
		
		System.out.println("defaultLoc    "+defaultLoc);
		
		String localeCode = defaultLoc.toLanguageTag();
		
		System.out.println("localeCode     "+localeCode);
		
		Locale locale = Locale.forLanguageTag(localeCode);
		
		
		System.err.println(locale==defaultLoc); // 주소비교 
		System.err.println(locale.equals(defaultLoc)); // 상태비교
		// 두 결과값은 모두 true , 
	}
	

	@Test
	public void test() {
//		System.out.println("test");
		
		Locale defaultLoc = Locale.getDefault();
		String lang = defaultLoc.getLanguage();
		String country = defaultLoc.getCountry();
		System.out.printf("1111111111111111 language : %s, country : %s, localeCode : %s \n",
				lang, country, defaultLoc.toString());
		
		// toString : Locale code
		
		System.out.printf("2222222222222  language : %s, country : %s \n",
				defaultLoc.getDisplayLanguage(), defaultLoc.getDisplayCountry());
		
		Locale[] locales = Locale.getAvailableLocales();
		
		for(Locale tmp : locales) {
			System.out.printf("language : %s, country : %s \n",
					tmp.getDisplayLanguage(tmp), tmp.getDisplayCountry(tmp));
			// 해당하는 나라에대하여 언어를 출력하기 위해 tmp를 넘겨준다.(?)
			
		}
		System.err.println("clclclcclcl");
		System.err.println("  "+lang.toString());
	}
}
