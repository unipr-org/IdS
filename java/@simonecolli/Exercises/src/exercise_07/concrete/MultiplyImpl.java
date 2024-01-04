package exercise_07.concrete;


import exercise_07.abstracts.ExprVisitor;
import exercise_07.abstracts.Expression;
import exercise_07.abstracts.Multiply;


public class MultiplyImpl implements Multiply {

	private Expression left_;
	private Expression right_;
	
	public MultiplyImpl(Expression left, Expression right) {
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
