package exercise_07.abstracts;

public interface ExprVisitor {
	public double visit(Expression v);
	public double visit(Add v);
	public double visit(SimpleNumber v);
	public double visit(Multiply v);
}
