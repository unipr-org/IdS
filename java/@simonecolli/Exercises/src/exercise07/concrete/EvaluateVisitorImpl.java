package exercise07.concrete;

import exercise07.abstracts.EvaluateVisitor;
import exercise07.abstracts.Expression;
import exercise07.abstracts.Visitable;
import exercise07.abstracts.ExprVisitor;

public class EvaluateVisitorImpl implements EvaluateVisitor{
	
	public EvaluateVisitorImpl() {
		
	}
	@Override
	public double evaluate(ExprVisitor v, Expression ve) {
		return ve.visit(v);
	}

}
