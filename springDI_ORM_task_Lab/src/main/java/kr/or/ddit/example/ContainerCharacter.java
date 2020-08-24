package kr.or.ddit.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.example.dao.ISampleDAO;
import kr.or.ddit.example.dao.SampleDAOImpl;

public class ContainerCharacter {
	
	public static void main(String args[]) {
		ConfigurableApplicationContext container =
				new ClassPathXmlApplicationContext("kr/or/ddit/example/conf/DI-containerDesc.xml");
		
		// container.registerShutdownHook();
		// 어플리케이션이 종료 될 때 메모리 정리후 종료가 됨 
		// 어플리케이션이 비정상 종료가 될경우 사용 
		
		ISampleDAO dao1 = container.getBean(SampleDAOImpl.class);
		ISampleDAO dao2 = container.getBean(SampleDAOImpl.class);
		
		System.out.println(dao1 == dao2);
		
		container.close();
		// 어플리케이션이 정상적인 종료 일때 : 웹에서는 고려할 필요가 없다. 
		
		System.exit(1);
	}

}
