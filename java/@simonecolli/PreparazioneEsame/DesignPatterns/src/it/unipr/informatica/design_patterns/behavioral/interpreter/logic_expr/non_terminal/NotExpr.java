package it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.non_terminal;

import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.BooleanExpression;
import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.Context;

public class NotExpr implements BooleanExpression{
	private BooleanExpression exp_;
	
	public NotExpr(BooleanExpression expr) {
		this.exp_ = expr;
	}
	@Override
	public boolean evaluate(Context context) {
		return !exp_.evaluate(context);
	}

}
