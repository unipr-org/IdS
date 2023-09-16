/*
 * BlockingQueue<T>
 * 
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.concurrent;

public interface BlockingQueue<T> {
	public void put(T e) throws InterruptedException;

	public T take() throws InterruptedException;

	public void clear();

	public int remainingCapacity();

	public boolean isEmpty();
}
