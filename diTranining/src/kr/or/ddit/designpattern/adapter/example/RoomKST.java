package kr.or.ddit.designpattern.adapter.example;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import kr.or.ddit.designpattern.adapter.example.conf.AdapterPatternContainerConfig;

@Component
public class RoomKST {
//	public ConcentKST concent = new ConcentKST();
	
	private ConcentKST concent;
	@Required
	@Inject
	public void setConcent(ConcentKST concent) {
		this.concent = concent;
	}

	public static void main(String[] args) {
		// 컨테이너에 객체 주입 받고 사용하기  new를 한번만 사용 
//		RoomKST room = new RoomKST();
//		PluggableKST samsung = new SamsungProduct();
//		PluggableKST lg = new LGProduct();
//		PluggableCN xiaomi = new XiaomiProduct(); 
//		room.concent.plugin(samsung);
//		room.concent.plugin(lg); 
////		room.concent.plugin(xiaomi);
//		room.concent.plugin(new AdapterPlug(xiaomi));
		
		
		//--------------------- 06.09
//		ConfigurableApplicationContext container = 
//				new ClassPathXmlApplicationContext("kr/or/ddit/designpattern/plug.xml");
//				new ClassPathXmlApplicationContext("kr/or/ddit/designpattern/adapter/example/conf/adapterpattern-di.xml");
		ConfigurableApplicationContext container = 
				new AnnotationConfigApplicationContext(AdapterPatternContainerConfig.class);
		
		RoomKST room = container.getBean(RoomKST.class);
		PluggableKST samsung =  container.getBean("samsung",PluggableKST.class);
		PluggableKST lg =  container.getBean("LG",PluggableKST.class);
		PluggableKST adapter = container.getBean("adapterPlug",AdapterPlug.class);
//		ConcentKST concent = container.getBean("concentKST",ConcentKST.class);
		
		room.concent.plugin(samsung);
		room.concent.plugin(lg);
		room.concent.plugin(adapter);		
	}
}









