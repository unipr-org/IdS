package it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.non_terminal;

import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.BooleanExpression;
import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.Context;

public class OrExpr implements BooleanExpression{
	
	private BooleanExpression left_;
	private BooleanExpression right_;
	
	public OrExpr(BooleanExpression left, BooleanExpression right) {
		this.left_ = left;
		this.right_ = right;
	}
	
	@Override
	public boolean evaluate(Context context) {
		return (left_.evaluate(context) || right_.evaluate(context));
	}

}
