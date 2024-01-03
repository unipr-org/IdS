package it.unipr.informatica.esercizi.esercizio_181.model;

import it.unipr.informatica.esercizi.esercizio_181.lp.Visitor;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class ImplicationImpl implements ImplicationFormula {

	private Formula left;
	private Formula right;
	
	public ImplicationImpl(Formula left, Formula right) {
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
