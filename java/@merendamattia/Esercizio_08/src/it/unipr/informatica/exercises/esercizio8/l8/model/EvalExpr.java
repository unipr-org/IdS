package it.unipr.informatica.exercises.esercizio8.l8.model;

public interface EvalExpr extends Expr {
	public Expr getFunctor();
	public Expr getArgument();
}
