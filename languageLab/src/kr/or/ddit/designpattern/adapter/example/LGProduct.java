package kr.or.ddit.designpattern.adapter.example;

public class LGProduct implements PluggableKST {

	@Override
	public void receiveElectricWithTwoLeg() {
		System.out.println("이핀짜리 LG 제품");
	}

}
