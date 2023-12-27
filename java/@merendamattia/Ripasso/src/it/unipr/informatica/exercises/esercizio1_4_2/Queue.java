package it.unipr.informatica.exercises.esercizio1_4_2;

public interface Queue<T> {
	public void enqueue(T elem);
	public T dequeue();
	public void clear();
	public boolean isEmpty();
}
