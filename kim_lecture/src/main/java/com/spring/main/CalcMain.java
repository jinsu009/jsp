package com.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.spring.calc.Calculator;

public class CalcMain {

	public static void main(String[] args) {
//		Calculator cal = new Calculator();
		
		ApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:com/spring/context/application-context.xml");
		
		Calculator cal = ctx.getBean("calc", Calculator.class);
		
		cal.sum(1,2);
		cal.sum(1,2,3);
	}

}
