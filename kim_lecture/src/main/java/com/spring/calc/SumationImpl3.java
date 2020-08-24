package com.spring.calc;

public class SumationImpl3 implements Sumation{

	@Override
	public int sum(int a, int b) {
		// TODO Auto-generated method stub
		return a+b+100000000;
	}

	@Override
	public int sum(int a, int b, int c) {
		// TODO Auto-generated method stub
		return a+b+c+100000000;
	}

}
