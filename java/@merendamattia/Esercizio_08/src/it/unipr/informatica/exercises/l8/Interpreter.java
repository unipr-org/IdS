package it.unipr.informatica.exercises.l8;

import it.unipr.informatica.exercises.l8.model.AddExpr;
import it.unipr.informatica.exercises.l8.model.CondExpr;
import it.unipr.informatica.exercises.l8.model.DefExpr;
import it.unipr.informatica.exercises.l8.model.EvalExpr;
import it.unipr.informatica.exercises.l8.model.Expr;
import it.unipr.informatica.exercises.l8.model.Func;
import it.unipr.informatica.exercises.l8.model.Id;
import it.unipr.informatica.exercises.l8.model.Int;
import it.unipr.informatica.exercises.l8.model.MultiplyExpr;
import it.unipr.informatica.exercises.l8.model.Script;
import it.unipr.informatica.exercises.l8.model.SimpleL8Factory;
import it.unipr.informatica.exercises.l8.model.SubtractExpr;
import it.unipr.informatica.exercises.l8.model.Visitor;

public class Interpreter {	
	public void run(Script script) {
		Visitor visitor = new InnerVisitor();
		script.accept(visitor);
	}
	
	public static class InnerVisitor implements Visitor {
		private Expr current;
		
		@Override
		public void visit(Script script) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void visit(DefExpr defExpr) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void visit(CondExpr condExpr) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void visit(EvalExpr evalExpr) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void visit(AddExpr addExpr) {
			Expr left = addExpr.getLeft();
			left.accept(this);
			if(!(current instanceof Int))
				throw new IllegalArgumentException("!(current instanceof Int)");
			Int leftInt = (Int) left;
			
			Expr right = addExpr.getRight();
			right.accept(this);
			if(!(current instanceof Int))
				throw new IllegalArgumentException("!(current instanceof Int)");
			Int rightInt = (Int) right;
			
			int result = leftInt.getValue() + rightInt.getValue();
			
			current = SimpleL8Factory.INSTANCE.newInt(result);
		}

		@Override
		public void visit(SubtractExpr subtractExpr) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void visit(MultiplyExpr multiplyExpr) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void visit(Func func) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void visit(Int value) {
			current = value;
		}

		@Override
		public void visit(Id value) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
