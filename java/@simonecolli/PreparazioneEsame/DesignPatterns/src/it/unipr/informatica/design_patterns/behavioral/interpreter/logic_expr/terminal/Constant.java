package it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.terminal;

import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.BooleanExpression;
import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.Context;

public class Constant implements BooleanExpression{
	private boolean value_;
	public Constant(boolean value) {
		value_ = value;
	}
	@Override
	public boolean evaluate(Context context) {
		return value_;
	}
}
