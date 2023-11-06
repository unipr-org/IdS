package it.unipr.informatica.concurrent;

public interface BlockingQueue<T> {
	public void put(T e) throws InterruptedException;
	public T take() throws InterruptedException;
	public void clear();
	public int remainingCapacity();
	public boolean isEmpty();
}

// Non e' una queue perche' non c'e' `extends Queue`