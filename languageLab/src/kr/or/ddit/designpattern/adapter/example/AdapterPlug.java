package kr.or.ddit.designpattern.adapter.example;

public class AdapterPlug implements PluggableKST {

	private PluggableCN cnProduct;	
	public AdapterPlug(PluggableCN cnProduct) {
		super();
		this.cnProduct = cnProduct;
	}

	@Override
	public void receiveElectricWithTwoLeg() {
		cnProduct.receiveElectricWithThreeLeg();
	}

}
