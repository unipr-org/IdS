package it.unipr.informatica.concurrent.pool;

import it.unipr.informatica.concurrent.pool.exception.ExecutionException;

public interface Future<T> {
	public T get() throws InterruptedException, ExecutionException;

	public boolean isDone();
}