package it.unipr.informatica.design_patterns.exercises.undoQueue.abstracts;

public interface Queue<T> {
	public void enqueue(T elem);
	public T dequeue();
	public void clear();
	public boolean isEmpty();
}
