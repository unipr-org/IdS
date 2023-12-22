package it.unipr.informatica.esercizi.esercizio_111.Abstractions;

import java.util.Collection;

import it.unipr.informatica.esercizi.esercizio_111.NodeType;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public abstract class NodeComposite extends Node {
	// Node operations
	public abstract NodeType getType();
	
	
	// Composite operations
	@Override
	public Node getComposite() {
		return this;
	}
	public abstract void add(Node node);
	public abstract Collection<Node> remove(String name);
	public abstract Collection<Node> getChildren();
}
