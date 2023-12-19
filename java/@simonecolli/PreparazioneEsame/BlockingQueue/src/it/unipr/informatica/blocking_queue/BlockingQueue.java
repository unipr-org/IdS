package it.unipr.informatica.blocking_queue;

public interface BlockingQueue<T> {
	public T take() throws InterruptedException;
	public void put(T elem) throws InterruptedException;
	public void clear();
	public int remainingCapacity();
	public boolean isEmpty();
	public boolean isFull();
}
