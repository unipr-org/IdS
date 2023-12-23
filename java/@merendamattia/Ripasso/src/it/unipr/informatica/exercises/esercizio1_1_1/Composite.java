package it.unipr.informatica.exercises.esercizio1_1_1;

import java.util.Collection;

public abstract class Composite extends Component {
	protected Collection<Component> children;
	
	public abstract Collection<Component> getChildren();
	
} // ! Composite
