package kr.or.ddit.designpattern;

import org.junit.Test;

import kr.or.ddit.designpattern.templatemethod.DerivedClass1;
import kr.or.ddit.designpattern.templatemethod.TemplateClass;

public class TemplateClassTest {

	@Test
	public void testTemplate() {
		
		TemplateClass templateClz = new DerivedClass1();
//		TemplateClass templateClz = new DerivedClass2();
		templateClz.template();
	}

}
