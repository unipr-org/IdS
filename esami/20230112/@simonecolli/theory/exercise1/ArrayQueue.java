package exam_2023_01_12.theory.exercise1;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayQueue implements Queue{

	private ArrayList<Object> list_;
	public ArrayQueue() {
		list_ = new ArrayList<>();
	}
	
	@Override
	public boolean isEmpty() {
		return list_.isEmpty();
	}

	@Override
	public void push(Object o) {
		list_.add(o);
	}

	@Override
	public Object pop() throws NoSuchElementException {
		Object front = list_.get(0);
		list_.remove(0);
		return front;
	}
}
