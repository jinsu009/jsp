package com.spring.calc;

public class Calculator {

//	private Sumation sum = new SumationImpl();
	private Sumation sum;
	
	public void setSum(Sumation sum) {
		this.sum = sum;
	}

	public void sum(int a, int b) {
		int result = sum.sum(a,b);
		System.out.println("두 정수 " + a + " 와 " + b + " 의 합 : " + result);
	}
	
	public void sum(int a, int b, int c) {
		int result = sum.sum(a,b,c);
		System.out.println("세 정수 " + a + " 와 " + b + c + " 의 합 : " + result);
	}
}
