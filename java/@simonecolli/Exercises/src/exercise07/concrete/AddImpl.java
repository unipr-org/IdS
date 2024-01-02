package exercise07.concrete;

import exercise07.abstracts.Add;

import exercise07.abstracts.Expression;
import exercise07.abstracts.ExprVisitor;

public class AddImpl implements Add {

	private Expression left_;
	private Expression right_;
	
	public AddImpl(Expression left, Expression right) {
		left_ = left;
		right_ = right;
	}
	
	@Override
	public double visit(ExprVisitor v) {
		return v.accept(this);
	}

	@Override
	public Expression getLeft() {
		return left_;
	}

	@Override
	public Expression getRight() {
		return right_;
	}

}
