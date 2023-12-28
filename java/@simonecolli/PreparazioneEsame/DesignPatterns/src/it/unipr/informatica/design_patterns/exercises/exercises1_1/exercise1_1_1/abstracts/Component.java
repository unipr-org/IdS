package it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.abstracts;

public abstract class Component {
	public abstract boolean add(Component component);
	public abstract boolean remove(Component component);
	public abstract Component getChild(int index);
	public abstract NodeType getNodeType();
	public abstract String getDescription();
}
