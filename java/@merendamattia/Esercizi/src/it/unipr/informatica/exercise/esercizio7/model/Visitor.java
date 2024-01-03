package it.unipr.informatica.exercise.esercizio7.model;

public interface Visitor {
	public void visit(Number item);
	public void visit(Add item);
	public void visit(Multiply item);
}
