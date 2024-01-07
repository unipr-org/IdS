package it.unipr.informatica.executors.model;

public interface Future<T> {
	public T get() throws InterruptedException, Throwable;
	public boolean isDone();
}
