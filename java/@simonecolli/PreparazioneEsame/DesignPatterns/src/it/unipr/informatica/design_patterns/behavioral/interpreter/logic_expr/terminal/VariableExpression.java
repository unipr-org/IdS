package it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.terminal;

import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.BooleanExpression;
import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.Context;

public class VariableExpression implements BooleanExpression{
	
	private String name_;
	public VariableExpression(String name) {
		name_ = name;
	}
	@Override
	public boolean evaluate(Context context) {
		return context.lookFor(name_);
	}
	
	public String getName() {
		return name_;
	}
}
