package it.unipr.informatica.esercizi.esercizio_111.Implementations;

import it.unipr.informatica.esercizi.esercizio_111.Abstractions.Node;
import it.unipr.informatica.esercizi.esercizio_111.Visitors.Visitor;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Leaf extends Node {
	
	public Leaf(String name) {
		this._name = name;
		this._type = NodeType.LEAF;
	}
	
	@Override
	public NodeType getType() {
		return this._type;
	}

	@Override
	public String getName() {
		return this._name;
	}

	@Override
	public void setName(String name) {
		this._name = name;
	}
	
	@Override
	public String toString() {
		return "[LEAF] " + _name;
	}

	@Override
	public void accept(Visitor v) {
		v.visitLeaf(this);
	}
}
