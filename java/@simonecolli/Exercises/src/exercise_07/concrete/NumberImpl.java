package exercise_07.concrete;

import exercise_07.abstracts.ExprVisitor;
import exercise_07.abstracts.SimpleNumber;

public class NumberImpl implements SimpleNumber {
	
	private double value_;
	
	public NumberImpl(double value) {
		value_ = value;
	}
	
	@Override
	public double accept(ExprVisitor v) {
		return v.visit(this);
	}
	

	@Override
	public double getValue() {
		return value_;
	}

}
