package it.unipr.informatica.blockingQueue;

public interface BlockingQueue<T> {
	public void put(T object) throws InterruptedException;
	public T take() throws InterruptedException;
	public void clear();
	public boolean isEmpty();
	public boolean isFull();
	public int remainingCapacity();
	public void print();
}
