package it.unipr.informatica.esercizi.esercizio_111.Iterators;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

import it.unipr.informatica.esercizi.esercizio_111.Abstractions.Node;
import it.unipr.informatica.esercizi.esercizio_111.Abstractions.NodeComposite;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class NodeDeepFirstIterator implements Iterator {
	protected Node _root;
	protected Node _currentItem;
	protected Stack<Node> _stack;
	
	public NodeDeepFirstIterator(Node root) {
		_root = root;
		_currentItem = _root;
		
		_stack = new Stack<Node>();
		_stack.push(_root);
	}
	
	@Override
	public Object first() {
		return this._root;
	}

	@Override
	public Object next() {
		Node nextNode = _stack.pop();
		
		Collection<Node> children;
		if (nextNode.getComposite() != null) {
			NodeComposite nextNodeComposite = (NodeComposite)(nextNode);
			children = nextNodeComposite.getChildren();
			
			LinkedList<Node> reverseChildren = new LinkedList<Node>();
			for (Node n : children)
				reverseChildren.addFirst(n);
			
			for (Node n : reverseChildren)
				_stack.push(n);			
		}
		
		_currentItem = nextNode;
		return nextNode;
	}

	@Override
	public Object currentItem() {
		return _currentItem;
	}

	@Override
	public boolean isDone() {
		return _stack.isEmpty();
	}

}
