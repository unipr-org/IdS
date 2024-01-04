package exercise_07.concrete;

import exercise_07.abstracts.EvaluateVisitor;

import exercise_07.abstracts.ExprVisitor;
import exercise_07.abstracts.Expression;

public class EvaluateVisitorImpl implements EvaluateVisitor{
	
	@Override
	public double evaluate(ExprVisitor v, Expression ve) {
		return ve.accept(v);
	}

}
