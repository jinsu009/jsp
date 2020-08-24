package kr.or.ddit.designpattern.strategy.example;

import org.springframework.stereotype.Component;

@Component("RiffleGun")
public class RiffleGun implements Gun {

	@Override
	public void shot() {
		System.out.println("RiffleGun");

	}

}
