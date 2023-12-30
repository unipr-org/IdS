package it.unipr.informatica.exercises.esercizio8.l8.model;

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
		return new InnerDefExpr(name, value);
	}

	@Override
	public CondExpr newCondExpr(Expr condition, Expr then, Expr otherwise) {
		return new InnerCondExpr(condition, then, otherwise);
	}

	@Override
	public EvalExpr newEvalExpr(Expr functor, Expr argument) {
		return new InnerEvalExpr(functor, argument);
	}

	@Override
	public AddExpr newAddExpr(Expr left, Expr right) {
		return new InnerAddExpr(left, right);
	}

	@Override
	public SubtractExpr newSubtractExpr(Expr left, Expr right) {
		return new InnerSubtractExpr(left, right);
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
	private class InnerDefExpr implements DefExpr {
		private Id name;
		private Expr value;
		
		public InnerDefExpr(Id name, Expr value) {
			if(name == null || value == null)
				throw new IllegalArgumentException("name == null || value == null");
			
			this.name = name;
			this.value = value;
		}

		@Override
		public Id getName() { return name; }

		@Override
		public Expr getValue() { return value; }
		
		@Override
		public void accept(Visitor visitor) {
			// TODO Auto-generated method stub	
		}
	} // ! InnerDefExpr
	
	private class InnerCondExpr implements CondExpr {
		private Expr condition;
		private Expr then;
		private Expr otherwise;

		public InnerCondExpr(Expr condition, Expr then, Expr otherwise) {
			if(condition == null || then == null || otherwise == null)
				throw new IllegalArgumentException("condition == null || then == null || otherwise == null");
			
			this.condition = condition;
			this.then = then;
			this.otherwise = otherwise;
		}
		
		@Override
		public Expr getCondition() { return condition; }

		@Override
		public Expr getThen() { return then; }

		@Override
		public Expr getElse() { return otherwise; }
		
		@Override
		public void accept(Visitor visitor) {
			// TODO Auto-generated method stub
		}
	} // ! InnerCondExpr
	
	private class InnerEvalExpr implements EvalExpr {
		private Expr functor;
		private Expr argument;

		public InnerEvalExpr(Expr functor, Expr argument) {
			if(functor == null || argument == null)
				throw new IllegalArgumentException("functor == null || argument == null");
			
			this.functor = functor;
			this.argument = argument;
		}

		@Override
		public Expr getFunctor() { return functor; }

		@Override
		public Expr getArgument() { return argument; }
		
		@Override
		public void accept(Visitor visitor) {
			// TODO Auto-generated method stub
		}
	} // ! InnerEvalExpr
	
	private class InnerAddExpr implements AddExpr {
		private Expr left;
		private Expr right;
		
		public InnerAddExpr(Expr left, Expr right) {
			if(left == null || right == null)
				throw new IllegalArgumentException("left == null || right == null");
			
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
	} // ! InnerAddExpr
	
	private class InnerSubtractExpr implements SubtractExpr {
		private Expr left;
		private Expr right;
		
		public InnerSubtractExpr(Expr left, Expr right) {
			if(left == null || right == null)
				throw new IllegalArgumentException("left == null || right == null");
			
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
	} // ! InnerSubtractExpr
	
	private class InnerMultiplyExpr implements MultiplyExpr {
		private Expr left;
		private Expr right;
		
		public InnerMultiplyExpr(Expr left, Expr right) {
			if(left == null || right == null)
				throw new IllegalArgumentException("left == null || right == null");
			
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
			if(body == null || argument == null)
				throw new IllegalArgumentException("body == null || argument == null");
			
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
				throw new IllegalArgumentException("value == null");
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
