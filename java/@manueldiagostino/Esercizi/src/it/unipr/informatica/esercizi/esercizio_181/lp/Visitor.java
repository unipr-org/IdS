package it.unipr.informatica.esercizi.esercizio_181.lp;

import it.unipr.informatica.esercizi.esercizio_181.model.*;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Visitor {
	public void visit(NotFormula f);
	public void visit(AndFormula f);
	public void visit(OrFormula f);
	public void visit(ImplicationFormula f);
	public void visit(DoubleImplicationFormula f);
	public void visit(Atom a);
}
