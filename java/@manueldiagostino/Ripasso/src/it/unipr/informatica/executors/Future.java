package it.unipr.informatica.executors;

public interface Future <RetType> {
	public RetType get() throws InterruptedException, Throwable;
	public boolean isDone();
}
