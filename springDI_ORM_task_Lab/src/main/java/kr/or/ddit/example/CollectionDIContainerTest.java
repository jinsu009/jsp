package kr.or.ddit.example;

import java.awt.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.example.vo.CollectionDIVO;

public class CollectionDIContainerTest {

	public static void main(String args[]) {
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/example/conf/CollectionDI-container.xml");
		
		CollectionDIVO collectionvo = context.getBean("vo2", CollectionDIVO.class);
		
		System.out.println(collectionvo.getList());
		System.out.println(collectionvo.getArray());
		System.out.println(collectionvo.getMap());
		System.out.println(collectionvo.getProps());
		System.out.println(collectionvo.getSet());
		
//		context.getBean(List.class); // 등록은 유틸이지만 bean이다. 
//		String[] names = context.getBeanDefinitionNames();
		String[] names = context.getBeanNamesForType(String[].class);
		for(String tmp : names) {
			System.out.println(tmp);
		}
		
	}
}
