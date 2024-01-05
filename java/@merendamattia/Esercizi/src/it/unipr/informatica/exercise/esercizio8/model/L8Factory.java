package it.unipr.informatica.exercise.esercizio8.model;

import java.util.List;

public interface L8Factory {
	public Script newScript(List<GlobalExpr> exprs);
	public DefExpr newDefExpr(Id name, Expr value);
	public CondExpr newCondExpr(Expr condition, Expr then, Expr otherwise);
	public EvalExpr newEvalExpr(Expr function, Expr argument);
	public AddExpr newAddExpr(Expr left, Expr right);
	public SubtractExpr newSubtractExpr(Expr left, Expr right);
	public MultiplyExpr newMultiplyExpr(Expr left, Expr right);
	public Func newFunc(Id argument, Expr body);
	public Int newInt(int value);
	public Id newId(String lexeme);
}
