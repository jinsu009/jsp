package kr.or.ddit.designpattern.strategyexample;

public class Solidier{

	private Gun gun;
	
	public void armedWithGun(Gun gun) {
		this.gun = gun;
	}

	public void attack() {
		gun.shot();
	}
	
}
