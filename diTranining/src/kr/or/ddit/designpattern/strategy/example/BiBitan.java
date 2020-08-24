package kr.or.ddit.designpattern.strategy.example;

import org.springframework.stereotype.Component;

@Component("BiBitan")
public class BiBitan implements Gun {

	@Override
	public void shot() {
		System.out.println("BiBiTan");

	}

}
