package it.unipr.informatica.exercises.esercizio1_1_1;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Region extends Composite {

	public Region(String name) {
		this.name = name;
		this.type = ComponentType.REGION;
		this.children = new LinkedList<Component>();
	}
	
	@Override
	public Collection<Component> getChildren() {
		return children;
	}

	@Override
	public void add(Component item) {
		if(item instanceof District)
			children.add(item);
		else
			System.err.println("[ERROR] " + this + " ammette solo District, hai provato ad inserire " + item);
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
		String result = this.toString();
		
		Iterator<Component> it = children.iterator();
		while(it.hasNext()) {
			result += "\n\t" + it.next().print();
		}
		
		return result;
	}

} // ! Region
