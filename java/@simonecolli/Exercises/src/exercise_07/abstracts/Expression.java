package exercise_07.abstracts;

public interface Expression {
	public double accept(ExprVisitor v);
}
