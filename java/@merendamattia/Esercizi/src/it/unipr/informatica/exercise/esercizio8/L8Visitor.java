package it.unipr.informatica.exercise.esercizio8;

import java.util.Iterator;

import it.unipr.informatica.exercise.esercizio8.model.*;

public class L8Visitor implements Visitor {
	private int current;
	
	@Override
	public void visit(Script script) {
		Iterator<GlobalExpr> it = script.iterator();
		while(it.hasNext()) {
			it.next().accept(this);
		}
	}

	@Override
	public void visit(DefExpr expr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CondExpr expr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(EvalExpr expr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AddExpr expr) {
		Expr left = expr.getLeft();
		left.accept(this);
		
		int result = current;
		
		Expr right = expr.getRight();
		right.accept(this);
		
		current = current + result;
	}

	@Override
	public void visit(SubtractExpr expr) {
		Expr left = expr.getLeft();
		left.accept(this);
		
		int result = current;
		
		Expr right = expr.getRight();
		right.accept(this);
		
		current = current - result;
	}

	@Override
	public void visit(MultiplyExpr expr) {
		Expr left = expr.getLeft();
		left.accept(this);
		
		int result = current;
		
		Expr right = expr.getRight();
		right.accept(this);
		
		current = current * result;
	}

	@Override
	public void visit(Func func) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Int value) {
		current = value.getInt();
	}

	@Override
	public void visit(Id value) {
		// TODO Auto-generated method stub
		
	}

} // ! L8Visitor
