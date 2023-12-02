package it.unipr.informatica.concurrent.locks;

public interface BlockingQueue<T> {
	public void put(T elem) throws InterruptedException;
	public T take() throws InterruptedException;
	
	public void clear();
	public int remainingCapacity();
	public boolean isEmpty();
}
