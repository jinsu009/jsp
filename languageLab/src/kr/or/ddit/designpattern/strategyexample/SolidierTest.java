package kr.or.ddit.designpattern.strategyexample;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolidierTest {

	@Test
	public void testAttach() {
		Gun gun = new ShotGun();
		Solidier solidier = new Solidier();
		solidier.armedWithGun(gun);
		solidier.attack();
	}

}
