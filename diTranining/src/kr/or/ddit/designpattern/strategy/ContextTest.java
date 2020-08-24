package kr.or.ddit.designpattern.strategy;

import org.junit.Test;

public class ContextTest {

	@Test
	public void testOperation() {
//		Sort strategy = new BinarySort();
		Sort strategy = new BubbleSort();
		Context ctx = new Context();
		ctx.setStrategy(strategy);
		ctx.operation();
	} 

}
