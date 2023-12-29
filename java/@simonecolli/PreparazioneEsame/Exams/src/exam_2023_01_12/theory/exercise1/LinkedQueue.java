package exam_2023_01_12.theory.exercise1;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LinkedQueue implements Queue {
	private LinkedList<Object> list_;
	public LinkedQueue() {
		list_ = new LinkedList<>();
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
