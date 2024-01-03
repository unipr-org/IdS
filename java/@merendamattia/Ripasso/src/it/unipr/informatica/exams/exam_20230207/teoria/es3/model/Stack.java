package it.unipr.informatica.exams.exam_20230207.teoria.es3.model;

public interface Stack {
	public void push(Object item);
	public Object pop() throws StackEmptyException;
}
