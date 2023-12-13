package it.unipr.informatica.exercises.l8.model;

public interface EvalExpr extends Expr {
	public Expr getFunctor();
	public Expr getArgument();
}
