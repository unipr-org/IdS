package exam_2023_02_07.theory.undoStack;

import exam_2023_02_07.theory.undoStack.abstracts.UndoStack;
import exam_2023_02_07.theory.undoStack.concrete.UndoStackImpl;

public class Main {
	public static void main(String[] args) {
		UndoStack stack = new UndoStackImpl();
		try {
			stack.undo();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		stack.push(10);
		stack.push(20);
		
		System.out.println("pop " + stack.pop()); // 20
		stack.undo();
		System.out.println("pop " + stack.pop()); // 20
		
		stack.push(100);
		stack.undo();
		System.out.println("pop " + stack.pop()); // 10
		
		
	}
}
