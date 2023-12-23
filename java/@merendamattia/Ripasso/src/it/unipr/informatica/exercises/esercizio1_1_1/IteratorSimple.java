package it.unipr.informatica.exercises.esercizio1_1_1;

import java.util.Collection;

public class IteratorSimple implements Iterator {

	private int index;
	private Collection<Component> children;
	
	public IteratorSimple(Composite node) {
		this.index = 0;
		this.children = node.getChildren();
	}
	
	@Override
	public Object first() {
		return children.toArray()[0];
	}

	@Override
	public Object next() {
		return children.toArray()[index++];
	}

	@Override
	public Object currentItem() {
		return children.toArray()[index];
	}

	@Override
	public boolean isDone() {
		if(index < children.size())
			return false;
		return true;
	}

} // ! IteratorSimple
