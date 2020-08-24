package kr.or.ddit.simple.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import kr.or.ddit.simple.OperatorType;

public class SimpleVO {

	@Min(Integer.MIN_VALUE)
	@Max(Integer.MAX_VALUE)
	private int leftOp; // int type은 파라미터가 없을경우 기본적으로 0이 들어간다. 
	private int rightOp;
	private int result;
	
	@NotNull
	private OperatorType operator;
	
	public OperatorType getOperator() {
		return operator;
	}
	public void setOperator(OperatorType operator) {
		this.operator = operator;
	}
	
	public int getLeftOp() {
		return leftOp;
	}
	public void setLeftOp(int leftOp) {
		this.leftOp = leftOp;
	}
	public int getRightOp() {
		return rightOp;
	}
	public void setRightOp(int rightOp) {
		this.rightOp = rightOp;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
	
}
