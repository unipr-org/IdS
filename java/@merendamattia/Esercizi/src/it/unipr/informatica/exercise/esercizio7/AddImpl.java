package it.unipr.informatica.exercise.esercizio7;

import it.unipr.informatica.exercise.esercizio7.model.Add;
import it.unipr.informatica.exercise.esercizio7.model.Expression;
import it.unipr.informatica.exercise.esercizio7.model.Visitor;

public class AddImpl implements Add {
	private Expression left;
	private Expression right;
	
	public AddImpl(Expression left, Expression right) {
		if(left == null || right == null)
			throw new IllegalArgumentException("left == null || right == null");
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
	public void accept(Visitor v) {
		v.visit(this);
	}
	
} // ! AddImpl
