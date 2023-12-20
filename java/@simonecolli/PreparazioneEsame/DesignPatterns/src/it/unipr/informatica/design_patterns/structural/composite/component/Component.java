package it.unipr.informatica.design_patterns.structural.composite.component;

import javax.management.RuntimeErrorException;

public abstract class Component {
	
	protected String name_;
	
	public Component(String name) {
		this.name_ = name;
	}
	
	public void add(Component component) throws SinglePartException {
		if(this instanceof SinglePart)
			throw new SinglePartException();
	};
	
	public boolean remove(Component component) throws SinglePartException {
		if(this instanceof SinglePart)
			throw new SinglePartException();
		
		return false;
	};
	
	public Component getChild(int index) {
		return null;
	}
	public abstract String toString();
	public abstract void describe();
	
}
