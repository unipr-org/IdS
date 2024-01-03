package it.unipr.informatica.exam.interfaces;

import it.unipr.informatica.exam.model.Add;
import it.unipr.informatica.exam.model.Multiply;
import it.unipr.informatica.exam.model.Number;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface VisitorExpr {
	public void visit(Number n);
	public void visit(Add expr);
	public void visit(Multiply expr);
}
