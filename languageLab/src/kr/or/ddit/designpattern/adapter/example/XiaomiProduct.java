package kr.or.ddit.designpattern.adapter.example;

public class XiaomiProduct implements PluggableCN {

	@Override
	public void receiveElectricWithThreeLeg() {
		System.out.println("세핀짜리 샤오미 제품");
	}

}
