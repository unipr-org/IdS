package it.unipr.informatica.exercises.esercizio1_1_1;

public abstract class Component {
	protected ComponentType type; 
	protected String name;
	
	public abstract void add(Component item);
	public abstract boolean remove(Component item);
	public abstract void operation();
	public abstract String print();
	
	@Override
	public String toString() {
		return type + " - " + name;
	}
	
	public enum ComponentType {
		LEAF, DISTRICT, REGION, COUNTRY
	}
}
