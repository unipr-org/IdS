package exam_2023_01_12.theory.exercise1;

import java.util.NoSuchElementException;

public interface Queue {
	public boolean isEmpty();
	public void push(Object o);
	public Object pop() throws NoSuchElementException;
}
