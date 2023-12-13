package it.unipr.informatica.exercises.l8.model;

public interface Visitor {
	public void visit(Script script);
	public void visit(DefExpr defExpr);
	public void visit(CondExpr condExpr);
	public void visit(EvalExpr evalExpr);
	public void visit(AddExpr addExpr);
	public void visit(SubtractExpr subtractExpr);
	public void visit(MultiplyExpr multiplyExpr);
	public void visit(Func func);
	public void visit(Int value);
	public void visit(Id value);
}
