package it.unipr.informatica.exercises.esercizio8.l8.model;

public interface CondExpr extends Expr {
	public Expr getCondition();
	public Expr getThen();
	public Expr getElse();
}
