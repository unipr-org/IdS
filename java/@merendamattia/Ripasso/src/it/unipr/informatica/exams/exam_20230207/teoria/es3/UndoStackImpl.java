package it.unipr.informatica.exams.exam_20230207.teoria.es3;

import it.unipr.informatica.exams.exam_20230207.teoria.es3.model.*;

public class UndoStackImpl implements UndoStack {
	private Object[] stack;
	private int size;
	private static final int CAPACITY = 256;
	private Command undo;
	
	public UndoStackImpl() {
		this.stack = new Object[CAPACITY];
		this.size = 0;
		this.undo = null;
	}

	@Override
	public void push(Object item) {
		if(size == CAPACITY)
			throw new IllegalArgumentException("size == CAPACITY");
		if(item == null)
			throw new IllegalArgumentException("item == null");
		
		stack[size] = item;
		size++;
		
		undo = new UndoCommand(() -> {
			size--;
			stack[size] = null;
		});
	}

	@Override
	public Object pop() throws StackEmptyException {
		if(size == 0)
			throw new StackEmptyException();
		
		size--;
		Object result = stack[size];
		stack[size] = null;
		
		undo = new UndoCommand(() -> {
			stack[size] = result;
			size++;
		});
		
		return result;
	}

	@Override
	public void undo() {
		undo.execute();
	}
	
	@Override
	public String toString() {
		String result = "[";
		for(int i = 0; i < size - 1; i++)
			result += stack[i] + ", ";
		result += stack[size - 1] + "]";
		return result;
	}
	
} // ! UndoStackImpl
