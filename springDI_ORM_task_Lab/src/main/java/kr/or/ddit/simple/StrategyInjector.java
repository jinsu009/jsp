package kr.or.ddit.simple;

public class StrategyInjector {
	
	// foo, bar, baz 사이에서는 결합력이 없다 
	// 그 결합력은 현재 클래스(injector)에 모여 있다 
	// 현재 이 클래스를 어플리케이션 외부에 빼서 별도의 container로 운영 한다. 
	public static void main(String args[]) {
		IBar bar = new Bar();
		IBaz baz = new Baz();
		Foo foo = new Foo(bar, baz);
	}

}
