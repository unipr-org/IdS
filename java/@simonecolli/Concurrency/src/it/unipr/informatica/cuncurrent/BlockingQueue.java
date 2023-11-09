package it.unipr.informatica.cuncurrent;

public interface BlockingQueue<T> {
	
	public void put(T e) throws InterruptedException;
	
	public T take() throws InterruptedException;
	
	public void clear();
	
	public int remainingCapacity();
	
	public boolean isEmpty();
}
