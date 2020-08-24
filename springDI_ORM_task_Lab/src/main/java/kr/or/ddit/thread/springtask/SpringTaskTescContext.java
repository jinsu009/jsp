package kr.or.ddit.thread.springtask;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTaskTescContext {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("kr/or/ddit/thread/springtask/conf/task-context.xml");
		context.registerShutdownHook();
		
		
	}
}
