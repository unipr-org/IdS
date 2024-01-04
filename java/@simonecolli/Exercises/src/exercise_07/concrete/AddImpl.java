package exercise_07.concrete;

import exercise_07.abstracts.Add;
import exercise_07.abstracts.ExprVisitor;
import exercise_07.abstracts.Expression;

public class AddImpl implements Add {

	private Expression left_;
	private Expression right_;
	
	public AddImpl(Expression left, Expression right) {
		left_ = left;
		right_ = right;
	}
	
	@Override
	public Expression getLeft() {
		return left_;
	}

	@Override
	public Expression getRight() {
		return right_;
	}

	@Override
	public double accept(ExprVisitor v) {
		return v.visit(this);
	}

}
