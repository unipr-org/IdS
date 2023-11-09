package it.unipr.informatica.concurrent;

public interface Callable<T> {
	public T call() throws Exception;
}
