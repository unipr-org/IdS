package it.unipr.informatica.locks;

public interface Condition {
	public void await() throws InterruptedException;
	public void signal();
	public void signalAll();
}
