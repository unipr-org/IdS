package it.unipr.informatica.exams.exam_20230207.teoria.es3;

import it.unipr.informatica.exams.exam_20230207.teoria.es3.model.*;

public class Main {
	private void go() {
		UndoStack stack = new UndoStackImpl();
		stack.push("ciao");
		stack.push("a");
		stack.push("tutti");
		
		System.out.println(stack);
		
		try {
			System.out.println(stack.pop());
		} catch (StackEmptyException e) {
			System.err.println(e.getCause());
		}
		
		System.out.println(stack);
		
		stack.undo();
		
		System.out.println(stack);
		
		stack.push("cane");
		
		System.out.println(stack);
		
		stack.undo();
		
		System.out.println(stack);
	}
	public static void main(String[] args) {
		new Main().go();
	}
} // ! Main
