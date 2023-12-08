package it.unipr.informatica.blocking_queue;

public interface BlockingQueue<T> {
	public T take() throws InterruptedException;
	public void put(T value) throws InterruptedException;
	public void clear();
	public boolean isEmpty();
	public boolean isFull();
	public int remainingCapacity();
	public void print();
}
