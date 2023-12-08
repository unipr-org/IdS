package it.unipr.informatica.executors;

public interface Callable<T> {
	public T call() throws Throwable;
}
