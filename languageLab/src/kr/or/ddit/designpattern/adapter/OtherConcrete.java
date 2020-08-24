package kr.or.ddit.designpattern.adapter;

public class OtherConcrete implements Target {
	// 05.15
	
	@Override
	public void request() {
		System.out.println("Target의 직접적인 구현체");

	}

}
