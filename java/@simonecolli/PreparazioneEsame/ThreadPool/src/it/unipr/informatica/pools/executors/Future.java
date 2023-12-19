package it.unipr.informatica.pools.executors;

public interface Future<RetType> {
	public Object get() throws InterruptedException, Throwable;
	public boolean isDone();
}
