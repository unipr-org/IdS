package exercise_07;

import exercise_07.abstracts.EvaluateVisitor;
import exercise_07.abstracts.ExprVisitor;
import exercise_07.abstracts.Expression;
import exercise_07.concrete.AddImpl;
import exercise_07.concrete.EvaluateVisitorImpl;
import exercise_07.concrete.ExprVisitorImpl;
import exercise_07.concrete.MultiplyImpl;
import exercise_07.concrete.NumberImpl;

public class Main {
	public static void main(String[] args) {
		Expression expr = new MultiplyImpl
		(
			new AddImpl(new NumberImpl(10), new NumberImpl(50)),
			new NumberImpl(40)
		);
		
		ExprVisitor visitor = new ExprVisitorImpl();
		
		EvaluateVisitor evaluator = new EvaluateVisitorImpl();
		
		System.out.println("evaluating " + evaluator.evaluate(visitor, expr));
	}

}
