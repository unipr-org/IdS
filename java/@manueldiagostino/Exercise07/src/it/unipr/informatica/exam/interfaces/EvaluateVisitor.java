package it.unipr.informatica.exam.interfaces;

import it.unipr.informatica.exam.model.Add;
import it.unipr.informatica.exam.model.Multiply;
import it.unipr.informatica.exam.model.Number;
import it.unipr.informatica.exam.model.Expression;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class EvaluateVisitor implements VisitorExpr {

	private double result;
	
	public EvaluateVisitor() {
		this.result = 0.0;
	}
	
	@Override
	public void visit(Number n) {
		result = n.getValue();
	}

	@Override
	public void visit(Add expr) {
		Expression left = expr.getLeft();
		Expression right = expr.getRight();
		
		double partialRes = 0.0;
		
		left.accept(this);
		partialRes += result;
		
		right.accept(this);
		partialRes += result;
		
		result = partialRes;
	}

	@Override
	public void visit(Multiply expr) {
		Expression left = expr.getLeft();
		Expression right = expr.getRight();
		
		double partialRes = 1;
		
		left.accept(this);
		partialRes *= result;
		
		right.accept(this);
		partialRes *= result;
		
		result = partialRes;
	}
	
	public double getResult() {
		return this.result;
	}
	
}
