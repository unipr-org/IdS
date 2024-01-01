package exercise07.concrete;

import exercise07.abstracts.SimpleNumber;
import exercise07.abstracts.ExprVisitor;

public class NumberImpl implements SimpleNumber {
	
	private double value_;
	
	public NumberImpl(double value) {
		value_ = value;
	}
	
	@Override
	public double visit(ExprVisitor v) {
		return v.accept(this);
	}

	@Override
	public double getValue() {
		return value_;
	}

}
