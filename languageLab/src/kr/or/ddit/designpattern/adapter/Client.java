package kr.or.ddit.designpattern.adapter;

public class Client {
	// 05.15
	
	private Target target = new Adapter(new Adaptee());
	
	public static void main(String[] args) {
		new Client().target.request();
	}
	
}
