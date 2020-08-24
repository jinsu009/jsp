package kr.or.ddit.designpattern.templatemethod;

public abstract class TemplateClass {
	
	// template 메소드
	public final void template() {
		// final을 붙여줌으로써 절대로 변하지 않게 한다.
		stepOne();
		stepTwo();
		stepThree();
	}
	
	// 후크 메소드 :끌어다 사용하는 메소드 
	private void stepOne() {
		System.out.println("stepOne");
	};
	
	protected abstract void stepTwo();
	// 자식이 생성되는 순간 생성된다(?)
	
	private void stepThree() {
		System.out.println("stepThree");
	};	
	
	// 반드시 순서대로실행되어야한다 
	
}
