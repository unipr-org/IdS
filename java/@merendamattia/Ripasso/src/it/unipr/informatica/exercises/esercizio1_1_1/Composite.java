package it.unipr.informatica.exercises.esercizio1_1_1;

import java.util.Collection;

public abstract class Composite extends Component {
	// Variables
	protected Collection<Component> children;
	
	// Pattern Composite Methods
	public abstract Collection<Component> getChildren();
	
} // ! Composite
