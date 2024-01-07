package it.unipr.informatica.locks.model;

public interface Condition {
	public void await() throws InterruptedException;
	public void signal();
	public void signalAll();
}
