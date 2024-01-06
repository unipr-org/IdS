package it.unipr.informatica.exercises.esercizio_111.Iterators;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

import javax.management.RuntimeErrorException;

import it.unipr.informatica.exercises.esercizio_111.Abstractions.Node;
import it.unipr.informatica.exercises.esercizio_111.Abstractions.NodeComposite;
import it.unipr.informatica.exercises.esercizio_111.Implementations.NodeType;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class NodeSpecificTypeIterator implements Iterator {
	protected Node _root;
	protected Node _currentItem;
	protected NodeType _analyzedType;
	protected NodeDeepFirstIterator it;
	protected Stack<Node> _stack;

	public NodeSpecificTypeIterator(Node root, NodeType type) {
		_root = root;
		_currentItem = null;
		_analyzedType = type;
		it = new NodeDeepFirstIterator(root);
	}

	@Override
	public Object first() {
		return _root;
	}

	@Override
	public Object next() {
		if (it.isDone())
			throw new RuntimeException("No more nodes of requested type in the structure");

		Node nextNode = null;
		do {
			if (it.isDone()) {
				_currentItem = null;
				return _currentItem;
			}

			nextNode = (Node) it.next();
		} while (nextNode.getType() != _analyzedType);

		_currentItem = nextNode;
		return _currentItem;
	}

	@Override
	public Object currentItem() {
		return _currentItem;
	}

	@Override
	public boolean isDone() {
		return it.isDone();
	}

}
