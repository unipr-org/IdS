package it.unipr.informatica.exercises.l8.model;

public interface CondExpr extends Expr {
	public Expr getCondition();
	public Expr getThen();
	public Expr getElse();
}
