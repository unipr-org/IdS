package it.unipr.informatica.esercizi.esercizio_181.model;

import it.unipr.informatica.esercizi.esercizio_181.lp.Visitor;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class NotImpl implements NotFormula {
	private Formula argument;
	
	public NotImpl(Formula argument) {
		this.argument = argument;
	}
	
	@Override
	public Formula getArg() {
		return this.argument;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
