package it.unipr.informatica.exam.model;

import it.unipr.informatica.exam.interfaces.VisitorExpr;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class MultiplyImpl implements Multiply {

	private final Expression left;
	private final Expression right;
	
	public MultiplyImpl(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public Expression getLeft() {
		return left;
	}

	@Override
	public Expression getRight() {
		return right;
	}

	@Override
	public void accept(VisitorExpr visitor) {
		visitor.visit(this);
	}
	
}
