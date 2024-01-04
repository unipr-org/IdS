package exam_2023_02_07.theory.undoStack.abstracts;

import java.util.EmptyStackException;

public interface Stack {
	public void push(Object o);
	public Object pop() throws EmptyStackException;
}
