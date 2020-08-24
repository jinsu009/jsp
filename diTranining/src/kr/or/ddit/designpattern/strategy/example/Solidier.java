package kr.or.ddit.designpattern.strategy.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import kr.or.ddit.designpattern.adapter.example.XiaomiProduct;

@Component
public class Solidier{

	private XiaomiProduct xiomi;
		
	@Inject
	public void setXiomi(XiaomiProduct xiomi) {
		this.xiomi = xiomi;
	}
	
	public XiaomiProduct getXiomi() {
		return xiomi;
	}
	
	//라이프사이클콜백 : 리턴값은 void : return 값은 가져봤자 사용할 곳이 없다 
	// 파라미터 : 시그니쳐가 없으니까 못받음
	@PostConstruct
	public void init() {
		System.out.println("객체생성");
	}
	@PreDestroy
	public void destory() {
		System.out.println("객체소멸");
	}
	//-------------------
	private Gun gun;
//	@Inject
	@Resource(name="BiBitan")
	public void armedWithGun(Gun gun) {
		this.gun = gun;
	}
	public void attack() {
		gun.shot();
	}
	
}
