package it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr;

import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.non_terminal.AndExpr;
import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.non_terminal.NotExpr;
import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.non_terminal.OrExpr;
import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.terminal.Constant;
import it.unipr.informatica.design_patterns.behavioral.interpreter.logic_expr.terminal.VariableExpression;

public class Client {
	public static void main(String[] args) {
		VariableExpression p = new VariableExpression("p");
		VariableExpression q = new VariableExpression("q");
		
//		(true AND p) OR (q AND NOT(p))
		BooleanExpression expression = new OrExpr(
				new AndExpr(new Constant(true), p),
				new AndExpr(q, new NotExpr(p))
		);
		
		Context context = new Context();
		
		context.assign(p, true);
		context.assign(q, true);
//		(true AND true) OR (true AND NOT(true)) -> TRUE
		System.out.println("With:\n- p = true\n- q = true\nThre result is: " + expression.evaluate(context));
		
		context.assign(p, true);
		context.assign(q, false);
//		(true AND true) OR (false AND NOT(true)) -> TRUE
		System.out.println("With:\n- p = true\n- q = false\nThre result is: " + expression.evaluate(context));
		
		context.assign(p, false);
		context.assign(q, true);
//		(true AND false) OR (true AND NOT(false)) -> TRUE
		System.out.println("With:\n- p = false\n- q = true\nThre result is: " + expression.evaluate(context));
		
		context.assign(p, false);
		context.assign(q, false);
//		(true AND false) OR (false AND NOT(false)) -> FALSE
		System.out.println("With:\n- p = false\n- q = false\nThre result is: " + expression.evaluate(context));
	}
}
