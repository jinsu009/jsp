package kr.or.ddit.designpattern.strategy.example;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import kr.or.ddit.designpattern.adapter.example.conf.AdapterPatternContainerConfig;
import kr.or.ddit.designpattern.strategy.example.conf.StrategyExampleContainerConfig;

@Component
public class SolidierTest {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext parent = 
				new AnnotationConfigApplicationContext(AdapterPatternContainerConfig.class);
		
		ConfigurableApplicationContext child =  
				new ClassPathXmlApplicationContext(new String[] {"kr/or/ddit/designpattern/strategy/example/conf/strategy-context.xml"},parent);
//				new AnnotationConfigApplicationContext(StrategyExampleContainerConfig.class);
		
		child.registerShutdownHook();
//		Gun gun = child.getBean("BiBitan", Gun.class);
		Solidier solidier1 = child.getBean(Solidier.class);
		Solidier solidier2 = child.getBean(Solidier.class);
		Solidier solidier3 = child.getBean(Solidier.class);
		Solidier solidier4 = child.getBean(Solidier.class);
		
		
//		solidier1.armedWithGun(gun);
		solidier1.attack();
		solidier2.attack();
		solidier3.attack();
		solidier4.attack();
		System.out.println(solidier1.getXiomi());
	}
	
//	@Test
//	public void testAttach() {
//		//Gun gun = new ShotGun();
//		//Solidier solidier = new Solidier();
//		//----
//		ConfigurableApplicationContext container = 
//				new AnnotationConfigApplicationContext(StrategyExampleContainerConfig.class);
//		Gun shotGun = container.getBean("shotGun",Gun.class);
//		SolidierTest solidiertest = container.getBean(SolidierTest.class);
//		solidiertest.solidier.attack();
//	}

}
