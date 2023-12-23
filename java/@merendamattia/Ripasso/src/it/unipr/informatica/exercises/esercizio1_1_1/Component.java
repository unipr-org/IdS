package it.unipr.informatica.exercises.esercizio1_1_1;

public abstract class Component {
	// Variables
	protected ComponentType type; 
	protected String name;
	
	public enum ComponentType {
		LEAF, DISTRICT, REGION, COUNTRY
	}
	
	// Pattern Composite Methods
	public abstract void add(Component item);
	public abstract boolean remove(Component item);
	
	// Pattern Visitor Methods
	public abstract void accept(Visitor visitor); 
	
	// Other Methods
	public abstract void operation();
		
} // ! Component
