package it.unipr.informatica.exam;

import it.unipr.informatica.exam.interfaces.EvaluateVisitor;
import it.unipr.informatica.exam.model.AddImpl;
import it.unipr.informatica.exam.model.Expression;
import it.unipr.informatica.exam.model.MultiplyImpl;
import it.unipr.informatica.exam.model.NumberImpl;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Main {
	public static void main(String[] args) {
		Expression addExpr = new AddImpl(new NumberImpl(2), new NumberImpl(10));
		Expression mulExpr = new MultiplyImpl(new NumberImpl(2), new NumberImpl(4));
		Expression expr = new AddImpl(addExpr, mulExpr);
		
		EvaluateVisitor visitor = new EvaluateVisitor();
		expr.accept(visitor);
		
		System.out.println(visitor.getResult());
	}
}
