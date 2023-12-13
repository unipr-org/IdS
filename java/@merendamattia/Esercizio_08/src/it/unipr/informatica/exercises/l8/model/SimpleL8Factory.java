package it.unipr.informatica.exercises.l8.model;

import java.util.List;

public class SimpleL8Factory implements L8Factory {
	// Utilizzo design pattern Singleton
	public static volatile L8Factory INSTANCE = null;
	
	private SimpleL8Factory() {	}
	
	public static L8Factory getInstance() {
		if(INSTANCE == null)
			INSTANCE = new SimpleL8Factory();
		return INSTANCE;
	}
	// ! Singleton
	
	@Override
	public Script newScript(List<GlobalExpr> exprs) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DefExpr newDefExpr(Id name, Expr value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CondExpr newCondExpr(Expr condition, Expr then, Expr otherwise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EvalExpr newEvalExpr(Expr functor, Expr argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddExpr newAddExpr(Expr left, Expr right) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubtractExpr newSubtractExpr(Expr left, Expr right) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultiplyExpr newMultiplyExpr(Expr left, Expr right) {
		return new InnerMultiplyExpr(left, right);
	}

	@Override
	public Func newFunc(Id argument, Expr body) {
		return new InnerFunc(argument, body);
	}

	@Override
	public Int newInt(int value) {
		return new InnerInt(value);
	}

	@Override
	public Id newId(String lexeme) {
		return new InnerId(lexeme);
	}
	
	// ------------------------------------------ Inner Classes
	private class InnerMultiplyExpr implements MultiplyExpr {
		private Expr left;
		private Expr right;
		
		public InnerMultiplyExpr(Expr left, Expr right) {
			if(left == null)
				throw new NullPointerException("left == null");
			if(right == null)
				throw new NullPointerException("right == null");
			
			this.left = left;
			this.right = right;
		}
		
		@Override
		public Expr getLeft() { return left; }

		@Override
		public Expr getRight() { return right; }

		@Override
		public void accept(Visitor visitor) {
			// TODO Auto-generated method stub
			
		}
		
	} // ! InnerMultiplyExpr
	
	private class InnerFunc implements Func {
		private Id argument;
		private Expr body;
		
		public InnerFunc(Id argument, Expr body) {
			if(argument == null)
				throw new NullPointerException("argument == null");
			if(body == null)
				throw new NullPointerException("body == null");
			
			this.argument = argument;
			this.body = body;
		}
		
		@Override
		public Id getArgument() { return argument; }

		@Override
		public Expr getBody() { return body; }

		@Override
		public void accept(Visitor visitor) {
			// TODO Auto-generated method stub
			
		}
	} // ! InnerFunc
	
	private class InnerInt implements Int {
		private int value;
		
		public InnerInt(int value) { this.value = value; }

		@Override
		public int getValue() { return value; }

		@Override
		public void accept(Visitor visitor) {
			// TODO Auto-generated method stub
			
		}
	} // ! InnerInt

	private class InnerId implements Id {
		private String value;
		
		public InnerId(String value) {
			if(value == null)
				throw new NullPointerException("value == null");
			this.value = value;
		}
		
		@Override
		public String getLexeme() { return value; }

		@Override
		public void accept(Visitor visitor) {
			// TODO Auto-generated method stub
			
		}	
	} // ! InnerId
	
} // ! SimpleL8Factory
