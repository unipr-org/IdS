package it.unipr.informatica.exercise.esercizio7;

import it.unipr.informatica.exercise.esercizio7.model.Add;
import it.unipr.informatica.exercise.esercizio7.model.Multiply;
import it.unipr.informatica.exercise.esercizio7.model.Number;
import it.unipr.informatica.exercise.esercizio7.model.Visitor;

public class ExploreVisitor implements Visitor {
	
	@Override
	public void visit(Number item) {
		System.out.print(item.getValue());
	}

	@Override
	public void visit(Add item) {
		if(item == null)
			throw new IllegalArgumentException("item == null");
		
		System.out.print("( ");
		item.getLeft().accept(this);
		System.out.print(" + ");
		item.getRight().accept(this);
		System.out.print(" )");
	}

	@Override
	public void visit(Multiply item) {
		if(item == null)
			throw new IllegalArgumentException("item == null");
			
		System.out.print("( ");
		item.getLeft().accept(this);
		System.out.print(" * ");
		item.getRight().accept(this);
		System.out.print(" )");
	}

} // ! EvaluateVisitor
