package kr.or.ddit.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.example.vo.VariousDIVO;

public class VariousDIContainerTest {

	public static void main(String args[]) {
		// setter injection을 사용하고 있는 bean을 주입 
		
		// 선생님
		ConfigurableApplicationContext container 
			= new GenericXmlApplicationContext("classpath:kr/or/ddit/example/conf/VariousDI-container.xml");
		
		VariousDIVO vo2 = container.getBean("vo2",VariousDIVO.class);
		VariousDIVO vo4 = container.getBean("vo2",VariousDIVO.class);
		
		VariousDIVO vo1 = container.getBean("vo1",VariousDIVO.class);
		VariousDIVO vo3 = container.getBean("vo1",VariousDIVO.class);
		
		System.out.println(vo1 == vo3);
		System.out.println(vo2 == vo4);
		
//		ApplicationContext container = 
//				new ClassPathXmlApplicationContext("kr/or/ddit/example/conf/VariousDI-container.xml");
//		
//		VariousDIVO controller = container.getBean("vo2",VariousDIVO.class);
//		
//		System.out.println(controller);
	}
}
