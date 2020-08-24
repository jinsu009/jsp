package kr.or.ddit.enums;

public enum OperatorType{
	
	PLUS('+',(left, right)->{return left+right;}),
	MINUS('-', new Operator() {
		@Override
		public int operate(int left, int right) {
			return left-right;
		}
	}), //1.8 버전이 아닐경우 람다식은 사용할수 없으니까 익명구현을 해줘야한다.  
	MULTI('*',(left, right)->{return left*right;}), 
	DIVIDE('/',(left, right)->{return left/right;});
	// operator타입의 객체 
	// enum 은 기본적으로 name값을 가지고 있다.
	
	@FunctionalInterface
	public static interface Operator{
		// 작업을 어떤식으로 수행할것인지 명령을 내리는 부분을 저의하고 있다
		public int operate(int left, int right);
	}
	
	private char sign; 
	private Operator realOperator; //값이 바뀌는 변수가 아니다. 
	
	private OperatorType(char sign, Operator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	public int operate(int left, int right) {
		return realOperator.operate(left, right);
	}
	public char getSign() {
		return sign;
	}
	
	
}
