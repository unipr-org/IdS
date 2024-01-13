package it.unipr.informatica.aspects.active.queue;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface BlockingQueue<T> {
	public void put(T elem) throws InterruptedException;
	public T take() throws InterruptedException;
	
	public boolean isEmpty();
	public boolean isFull();
	public int size();
	public int remainingCapacity();
}
