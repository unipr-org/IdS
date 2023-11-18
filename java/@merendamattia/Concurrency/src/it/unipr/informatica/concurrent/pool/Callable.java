package it.unipr.informatica.concurrent.pool;

public interface Callable<T> {
	public T call() throws Exception;
}
