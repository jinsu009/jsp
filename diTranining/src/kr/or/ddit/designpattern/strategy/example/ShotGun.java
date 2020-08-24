package kr.or.ddit.designpattern.strategy.example;

import org.springframework.stereotype.Component;

public class ShotGun  implements Gun{

	@Override
	public void shot() {
		System.out.println("ShotGun");
		
	}

}
