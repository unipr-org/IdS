package it.unipr.informatica.esercizi.esercizio_181.model;

import it.unipr.informatica.esercizi.esercizio_181.lp.Visitor;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Formula {
	public void accept(Visitor v);
}
