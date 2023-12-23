package it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.non_terminal;

import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.BooleanExpression;
import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.Context;

public class AndExpr implements BooleanExpression{
	private BooleanExpression left_;
	private BooleanExpression right_;
	
	public AndExpr(BooleanExpression left, BooleanExpression right) {
		this.left_ = left;
		this.right_ = right;
	}
	
	@Override
	public boolean evaluate(Context context) {
		return (left_.evaluate(context) && right_.evaluate(context));
	}

}
