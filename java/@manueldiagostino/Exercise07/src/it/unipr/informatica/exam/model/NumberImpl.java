package it.unipr.informatica.exam.model;

import it.unipr.informatica.exam.interfaces.VisitorExpr;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class NumberImpl implements Number {

	private final double value;
	
	public NumberImpl(double value) {
		this.value = value;
	}
	
	@Override
	public double getValue() {
		return value;
	}

	@Override
	public void accept(VisitorExpr visitor) {
		visitor.visit(this);
	}

}
