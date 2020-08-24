package kr.or.ddit.designpattern.strategyexample;

public class ShotGun  implements Gun{

	@Override
	public void shot() {
		System.out.println("ShotGun");
		
	}

}
