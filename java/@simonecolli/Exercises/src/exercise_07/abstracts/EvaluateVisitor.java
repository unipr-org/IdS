package exercise_07.abstracts;

public interface EvaluateVisitor {
	public double evaluate(ExprVisitor v, Expression ve);
}
