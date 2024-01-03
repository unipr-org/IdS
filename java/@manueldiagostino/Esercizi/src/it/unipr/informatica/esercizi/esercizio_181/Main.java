package it.unipr.informatica.esercizi.esercizio_181;

import it.unipr.informatica.esercizi.esercizio_181.lp.*;
import it.unipr.informatica.esercizi.esercizio_181.model.*;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Main {
	public void testAnd() {
		InterpretationContextImpl context = new InterpretationContextImpl();
		
		Atom a1 = new AtomImpl("a1");
		Atom a2 = new AtomImpl("a2");
		context.bind(a1, true);
		context.bind(a2, true);
		
		Formula f = new AndImpl(a1, a2);
		
		LPInterpreter interpreter = new LPInterpreter(context);
		f.accept(interpreter);
		System.out.println("'"+context.getValue(a1)+ " and " +context.getValue(a2)+"'");
		System.out.println(interpreter.getResult());
		
		/**************************************/
		
		context.clear();
		Atom a3 = new AtomImpl("a3");
		context.bind(a1, true);
		context.bind(a2, true);
		context.bind(a3, false);
		
		Formula f1 = new AndImpl(a1, a2);
		Formula f2 = a3;
		f = new AndImpl(f1, f2);
		
		f.accept(interpreter);
		System.out.println("'"+context.getValue(a1)+ " and " +context.getValue(a2)+ " and " +context.getValue(a3)+"'");
		System.out.println(interpreter.getResult());
	}
	
	public void testComplexFormula() {
		InterpretationContextImpl context = new InterpretationContextImpl();
		
		Atom a1 = new AtomImpl("a1");
		Atom a2 = new AtomImpl("a2");
		Atom a3 = new AtomImpl("a3");
		Atom a4 = new AtomImpl("a4");
		context.bind(a1, true);
		context.bind(a2, false);
		context.bind(a3, false);
		context.bind(a4, true);
		
		ImplicationFormula i1 = new ImplicationImpl(a3, a4);
		OrFormula o1 = new OrImpl(a2, i1);
		AndFormula an1 = new AndImpl(a1, o1);
		
		Formula f = an1;
		
		LPInterpreter interpreter = new LPInterpreter(context);
		f.accept(interpreter);
		System.out.println("'"+context.getValue(a1)+" and ("+context.getValue(a2)+
				" or "+context.getValue(a3)+" -> "+context.getValue(a4)+")'");
		System.out.println(interpreter.getResult());	
	}
	
	
	public static void main(String[] args) {
		Main m = new Main();
		
		m.testAnd();
		m.testComplexFormula();
	}
}
