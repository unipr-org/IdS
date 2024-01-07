package it.unipr.informatica.executors.model;

public interface Callable<T> {
	public T call() throws Throwable;
}
