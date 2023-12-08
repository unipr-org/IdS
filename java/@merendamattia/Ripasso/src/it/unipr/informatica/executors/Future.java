package it.unipr.informatica.executors;

public interface Future<T> {
	public T get() throws InterruptedException, Throwable;
	public boolean isDone();
}
