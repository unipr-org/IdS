/*
 * Future<T>
 * 
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.concurrent;

public interface Future<T> {
	public T get() throws InterruptedException, ExecutionException;

	public boolean isDone();
}
