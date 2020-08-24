package kr.or.ddit.example.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.example.service.ISampleService;

public class SampleController {

	private ISampleService service;
	/**
	 * 
	 * 필수 전략인지 아닌지에 따라 생성자를 사용할것인지 생성자+setter를 사용할 것인지가 갈린다. 
	 * setter injection : optional 전략 , 필수 객체가 아니면 사용
	 * constructor injection : 필수 전략 , 주입할객체가 필수 객체 라면 사용
	 *  
	 */

	public SampleController(ISampleService service) {
		super();
		this.service = service;
		System.out.println(getClass().getSimpleName() + " 객체 생성, 생성자 주입 ");
	}
	
	public String commandProcess() {
		return service.readInformation();
	}
	
	public static void main(String[] args) {
		ApplicationContext container 
		= new ClassPathXmlApplicationContext("kr/or/ddit/example/conf/Sample-DI.xml");
		
		SampleController controller = container.getBean(SampleController.class);
		String info = controller.commandProcess();
		System.out.println(info);
	}
	
}
