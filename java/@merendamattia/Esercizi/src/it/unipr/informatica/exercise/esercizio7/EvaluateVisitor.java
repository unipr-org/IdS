package it.unipr.informatica.exercise.esercizio7;

import it.unipr.informatica.exercise.esercizio7.model.Add;
import it.unipr.informatica.exercise.esercizio7.model.Multiply;
import it.unipr.informatica.exercise.esercizio7.model.Number;
import it.unipr.informatica.exercise.esercizio7.model.Visitor;

public class EvaluateVisitor implements Visitor {
	private double current;
	
	public EvaluateVisitor() { this.current = 0; }
	
	public double getResult() { return current; }
	
	@Override
	public void visit(Number item) {
		current = item.getValue();
	}

	@Override
	public void visit(Add item) {
		if(item == null)
			throw new IllegalArgumentException("item == null");
		
		double result = 0;
		
		item.getLeft().accept(this);
		
		result = current;
		
		item.getRight().accept(this);
		
		result = result + current;
		current = result;
	}

	@Override
	public void visit(Multiply item) {
		if(item == null)
			throw new IllegalArgumentException("item == null");
			
		double result = 0;
		
		item.getLeft().accept(this);
		
		result = current;
		
		item.getRight().accept(this);
		
		result = result * current;
		current = result;
	}

} // ! EvaluateVisitor
