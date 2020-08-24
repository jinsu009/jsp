package kr.or.ddit.simple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DIContainerExample {
	public static void main(String arg[]) {
		ApplicationContext container 
		= new ClassPathXmlApplicationContext("kr/or/ddit/simple/conf/simple-di.xml");
		
		Foo foo = container.getBean("foo1",Foo.class); // 형변환이 필요가 없다. 
		
		System.out.println(foo);
		
		IBar bar= (IBar) container.getBean("bar");
	}
}
