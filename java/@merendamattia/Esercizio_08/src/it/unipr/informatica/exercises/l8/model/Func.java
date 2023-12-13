package it.unipr.informatica.exercises.l8.model;

public interface Func extends Expr {
	public Id getArgument();
	public Expr getBody();
}
