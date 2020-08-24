package kr.or.ddit.designpattern.adapter;

public class Adapter implements Target {
	// 05.15
	
	// adapter를 통해 adaptee를 사용하게 할수 있도록 생성자를 만들어준다.

	private Adaptee adaptee;
	
	public Adapter(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}
	
	@Override
	public void request() {
		adaptee.specificRequest();
	}


}
