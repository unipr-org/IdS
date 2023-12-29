package it.unipr.informatica.exercises.esercizio_111.Implementations;

import java.util.Collection;
import java.util.LinkedList;

import it.unipr.informatica.exercises.esercizio_111.Abstractions.Node;
import it.unipr.informatica.exercises.esercizio_111.Abstractions.NodeComposite;
import it.unipr.informatica.exercises.esercizio_111.Visitors.Visitor;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class District extends NodeComposite {
	protected Collection<Node> _children;
	
	public District(String name) {
		this._name = name;
		this._type = NodeType.DISTRICT;
		this._children = new LinkedList<Node>();
	}
	
	@Override
	public void add(Node node) {
		this._children.add(node);
	}

	@Override
	public Collection<Node> remove(String name) {
		LinkedList<Node> res = new LinkedList<Node>();
		
		for (Node n : _children)
			if (n.getName().equals(name)) {
				res.add(n);
				_children.remove(n);
			}
		
		return res;
	}

	@Override
	public Collection<Node> getChildren() {
		return this._children;
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
		return "[DISTRICT] " + _name;
	}

	@Override
	public void accept(Visitor v) {
		v.visitDistrict(this);
	}
}
