package exercise07.concrete;

import exercise07.abstracts.Add;
import exercise07.abstracts.SimpleNumber;
import exercise07.abstracts.ExprVisitor;
import exercise07.abstracts.Expression;
import exercise07.abstracts.Multiply;

public class ExprVisitorImpl implements ExprVisitor {
	
	
	@Override
	public double accept(Expression v) {
		
		if(v instanceof SimpleNumber) {
			System.out.println("number");
			return ((SimpleNumber)v).getValue();			
		} else if(v instanceof Add) {
			System.out.println("add");
			return ((Add) v).getLeft().visit(this) + ((Add) v).getRight().visit(this);			
		} else if(v instanceof Multiply) {
			System.out.println("multi");
			return ((Multiply) v).getLeft().visit(this) * ((Multiply) v).getRight().visit(this);
		} else
			throw new RuntimeException("unknown visitable");
		
	}


}
