package it.unipr.informatica.exercise.esercizio8.model;

public interface Visitor {
	public void visit(Script script);
	public void visit(DefExpr expr);
	public void visit(CondExpr expr);
	public void visit(EvalExpr expr);
	public void visit(AddExpr expr);
	public void visit(SubtractExpr expr);
	public void visit(MultiplyExpr expr);
	public void visit(Func func);
	public void visit(Int value);
	public void visit(Id value);
}
