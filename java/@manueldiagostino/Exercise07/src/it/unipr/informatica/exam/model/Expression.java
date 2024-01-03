package it.unipr.informatica.exam.model;

import it.unipr.informatica.exam.interfaces.VisitorExpr;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Expression {
	public void accept(VisitorExpr visitor);
}
