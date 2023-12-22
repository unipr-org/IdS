package it.unipr.informatica.exercises.esercizio1_1_1;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class District extends Composite {

	public District(String name) {
		this.name = name;
		this.type = ComponentType.DISTRICT;
		this.children = new LinkedList<Component>();
	}
	
	@Override
	public Collection<Component> getChildren() {
		return children;
	}

	@Override
	public void add(Component item) {
		if(item instanceof Leaf)
			children.add(item);
		else
			System.err.println("[ERROR] " + this + " ammette solo Leaf, hai provato ad inserire " + item);
	}

	@Override
	public boolean remove(Component item) {
		return children.remove(item);
	}

	@Override
	public void operation() {
		System.out.println(this + " doing something...");
	}

	@Override
	public String print() {
		String result = "\t" + this.toString();
		
		Iterator<Component> it = children.iterator();
		while(it.hasNext()) {
			result += "\n\t" + it.next().print();
		}
		
		return result;
	}

} // ! District
