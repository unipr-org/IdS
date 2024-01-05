package it.unipr.informatica.exercise.esercizio8.model;

public interface CondExpr extends Expr {
	public Expr getThen();
	public Expr getElse();
	public Expr getCondition();
}
