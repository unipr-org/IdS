package it.unipr.informatica.exercise.esercizio8;

import java.util.Iterator;
import java.util.List;

import it.unipr.informatica.exercise.esercizio8.model.AddExpr;
import it.unipr.informatica.exercise.esercizio8.model.CondExpr;
import it.unipr.informatica.exercise.esercizio8.model.DefExpr;
import it.unipr.informatica.exercise.esercizio8.model.EvalExpr;
import it.unipr.informatica.exercise.esercizio8.model.Expr;
import it.unipr.informatica.exercise.esercizio8.model.Func;
import it.unipr.informatica.exercise.esercizio8.model.GlobalExpr;
import it.unipr.informatica.exercise.esercizio8.model.Id;
import it.unipr.informatica.exercise.esercizio8.model.Int;
import it.unipr.informatica.exercise.esercizio8.model.L8Factory;
import it.unipr.informatica.exercise.esercizio8.model.MultiplyExpr;
import it.unipr.informatica.exercise.esercizio8.model.Script;
import it.unipr.informatica.exercise.esercizio8.model.SubtractExpr;
import it.unipr.informatica.exercise.esercizio8.model.Visitor;

public class L8FactoryImplementation implements L8Factory {
	public static L8FactoryImplementation INSTANCE = null;
	private L8FactoryImplementation() { }
	public static L8FactoryImplementation getInstance() {
		if(INSTANCE == null) {
			synchronized (L8FactoryImplementation.class) {
				if(INSTANCE == null)
					INSTANCE = new L8FactoryImplementation();
			}
		}
		return INSTANCE;
	}
	
	@Override
	public Script newScript(List<GlobalExpr> exprs) {
		if(exprs == null)
			throw new IllegalArgumentException("exprs == null");
		
		return new Script() {

			@Override
			public void accept(Visitor v) {
				v.visit(this);
			}

			@Override
			public Iterator<GlobalExpr> iterator() {
				return exprs.iterator();
			}
			
		}; // ! Script
	}

	@Override
	public DefExpr newDefExpr(Id name, Expr value) {
		if(name == null || value == null)
			throw new IllegalArgumentException("name == null || value == null");
		
		return new DefExpr() {
			
			@Override
			public void accept(Visitor v) {
				v.visit(this);
			}
			
			@Override
			public Id getId() {
				return name;
			}
			
			@Override
			public Expr getExpr() {
				return value;
			}
		}; // ! DefExpr
	
	}
	
	@Override
	public CondExpr newCondExpr(Expr condition, Expr then, Expr otherwise) {
		if(condition == null || then == null || otherwise == null)
			throw new IllegalArgumentException("condition == null || then == null || otherwise == null");
		
		return new CondExpr() {
			
			@Override
			public void accept(Visitor v) {
				v.visit(this);
			}
			
			@Override
			public Expr getThen() {
				return then;
			}
			
			@Override
			public Expr getElse() {
				return otherwise;
			}
			
			@Override
			public Expr getCondition() {
				return condition;
			}
		}; // ! CondExpr
	}

	@Override
	public EvalExpr newEvalExpr(Expr function, Expr argument) {
		if(function == null || argument == null)
			throw new IllegalArgumentException("function == null || argument == null");
		
		return new EvalExpr() {
			
			@Override
			public void accept(Visitor v) {
				v.visit(this);
			}

			@Override
			public Expr getFunction() {
				return function;
			}

			@Override
			public Expr getArgument() {
				return argument;
			}
		}; // ! EvalExpr
	}

	@Override
	public AddExpr newAddExpr(Expr left, Expr right) {
		if(left == null || right == null)
			throw new IllegalArgumentException("left == null || right == null");
		
		return new AddExpr() {
			
			@Override
			public void accept(Visitor v) {
				v.visit(this);
			}
			
			@Override
			public Expr getRight() {
				return right;
			}
			
			@Override
			public Expr getLeft() {
				return left;
			}
		}; // ! AddExpr
	}

	@Override
	public SubtractExpr newSubtractExpr(Expr left, Expr right) {
		if(left == null || right == null)
			throw new IllegalArgumentException("left == null || right == null");
		
		return new SubtractExpr() {
			
			@Override
			public void accept(Visitor v) {
				v.visit(this);
			}
			
			@Override
			public Expr getRight() {
				return right;
			}
			
			@Override
			public Expr getLeft() {
				return left;
			}
		}; // ! SubtractExpr
	}

	@Override
	public MultiplyExpr newMultiplyExpr(Expr left, Expr right) {
		if(left == null || right == null)
			throw new IllegalArgumentException("left == null || right == null");
		
		return new MultiplyExpr() {
			
			@Override
			public void accept(Visitor v) {
				v.visit(this);
			}
			
			@Override
			public Expr getRight() {
				return right;
			}
			
			@Override
			public Expr getLeft() {
				return left;
			}
		}; // ! MultiplyExpr
	}

	@Override
	public Func newFunc(Id argument, Expr body) {
		if(argument == null || body == null)
			throw new IllegalArgumentException("argument == null || body == null");
		
		return new Func() {

			@Override
			public void accept(Visitor v) {
				v.visit(this);
			}

			@Override
			public Id getId() {
				return argument;
			}

			@Override
			public Expr getExpr() {
				return body;
			}
			
		}; // ! Func
	}

	@Override
	public Int newInt(int value) {
		return new Int() {

			@Override
			public void accept(Visitor v) {
				v.visit(this);
			}

			@Override
			public int getInt() {
				return value;
			}
			
		}; // ! Int
	}

	@Override
	public Id newId(String lexeme) {
		if(lexeme == null)
			throw new IllegalArgumentException("lexeme == null");
		
		return new Id() {

			@Override
			public void accept(Visitor v) {
				v.visit(this);
			}

			@Override
			public String getLexame() {
				return lexeme;
			}
			
		}; // ! Id
	}

} // ! L8FactoryImplementation
