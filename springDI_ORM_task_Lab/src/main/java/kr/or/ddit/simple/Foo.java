package kr.or.ddit.simple;

public class Foo {
	
	// 1. 의존관계를 형성하는첫번째 방법 
	/**
	 * 의존 객체를 직접 생성 : 결합력이 높다
	 */
//	IBar bar = new Bar();
//	IBaz baz = new Baz();
	
	// 2. Factory pattern 
	/**
	 * 생성자를 사용하지 않고도 객체생성 의존성이 조금 낮다
	 * 받아오는 데이터에 명확성이 없다 
	 * 인터페이스로 추상화된 객체에 접근 
	 * 결합력을 낮춰준다. 
	 */
//	IBar bar = ObjectFactory.getBar();
//	IBaz baz = ObjectFactory.getBaz();
	
	// 3. 전략 패턴 (Strategy Pattern)
	// 생성자 혹은 setter를 만들어서 각각 값을 할당해준다.
	// 공장객체와 결합도가 남아 있지 않다. 
	// 전략의 주입자가 반드시 필요하다 
	private IBar bar;
	private IBaz baz;
	
	public Foo(IBar bar, IBaz baz) {
		super();
		this.bar = bar;
		this.baz = baz;
	}
	
	
	
	
}
