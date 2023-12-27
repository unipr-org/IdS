package it.unipr.informatica.esercizi.esercizio_142.Abstractions;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface UndoQueue<T> {
	public void enqueue(T elem);
	public T dequeue();
	public void clear();
	public boolean isEmpty();
	public void undo();
}
