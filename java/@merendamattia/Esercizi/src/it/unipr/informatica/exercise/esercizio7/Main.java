package it.unipr.informatica.exercise.esercizio7;

import it.unipr.informatica.exercise.esercizio7.model.Expression;
import it.unipr.informatica.exercise.esercizio7.model.Visitor;

public class Main {
	private void go() {
		Expression expr = new AddImpl(
							new MultiplyImpl(new NumberImpl(5), 
											 new NumberImpl(2)), 
							new MultiplyImpl(new NumberImpl(2), 
											 new NumberImpl(3))
					);
		
		Visitor explore = new ExploreVisitor();
		EvaluateVisitor evaluate = new EvaluateVisitor();
		
		expr.accept(explore);
		
		System.out.println();
		
		expr.accept(evaluate);
		
		System.out.println("Result = " + evaluate.getResult());
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
} // ! Main
