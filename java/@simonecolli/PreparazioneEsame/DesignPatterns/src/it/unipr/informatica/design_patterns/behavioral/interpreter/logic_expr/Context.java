package it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr;

import java.util.Hashtable;

import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.terminal.VariableExpression;

public class Context {
	private Hashtable<String, Boolean> vars_;
	public Context() {
		vars_ = new Hashtable<>();
	}
	public void assign(VariableExpression var, boolean value) {
		vars_.put(var.getName(), value);
	}
	public boolean lookFor(String varName) {
		return vars_.get(varName).booleanValue();
	}
}
