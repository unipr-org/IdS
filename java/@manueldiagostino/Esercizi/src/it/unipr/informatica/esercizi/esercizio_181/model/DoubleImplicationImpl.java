package it.unipr.informatica.esercizi.esercizio_181.model;

import it.unipr.informatica.esercizi.esercizio_181.lp.Visitor;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class DoubleImplicationImpl implements OrFormula {

	private Formula left;
	private Formula right;
	
	public DoubleImplicationImpl(Formula left, Formula right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public Formula getLeft() {
		return this.left;
	}

	@Override
	public Formula getRight() {
		return this.right;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
