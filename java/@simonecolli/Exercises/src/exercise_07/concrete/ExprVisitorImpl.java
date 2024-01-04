package exercise_07.concrete;

import exercise_07.abstracts.Add;
import exercise_07.abstracts.ExprVisitor;
import exercise_07.abstracts.Expression;
import exercise_07.abstracts.Multiply;
import exercise_07.abstracts.SimpleNumber;

public class ExprVisitorImpl implements ExprVisitor {
	

	@Override
	public double visit(Expression v) {
		if(v == null)
			throw new IllegalArgumentException("v == null");
		
		if(v instanceof SimpleNumber) {
			return visit((SimpleNumber)v);
		} else if(v instanceof Add) {
			System.out.println("add");			
			return visit((Add)v);
		} else if(v instanceof Multiply) {
			System.out.println("multi");
			return visit((Multiply)v);
		} else
			throw new RuntimeException("unknown visitable");
	}
	
	@Override
	public double visit(Add v) {
		if(v == null)
			throw new IllegalArgumentException("v == null");
		
		double left = v.getLeft().accept(this);
		double right = v.getRight().accept(this);
		return left + right;
	}
	
	@Override
	public double visit(Multiply v) {
		if(v == null)
			throw new IllegalArgumentException("v == null");
		
		double left = v.getLeft().accept(this);
		double right = v.getRight().accept(this);
		return left * right;
	}
	
	@Override
	public double visit(SimpleNumber v) {
		if(v == null)
			throw new IllegalArgumentException("v == null");
		
		return v.getValue();
	}


}
