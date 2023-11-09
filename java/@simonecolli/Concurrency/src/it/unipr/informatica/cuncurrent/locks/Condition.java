package it.unipr.informatica.cuncurrent.locks;

public interface Condition {
	public void await() throws InterruptedException;
	public void signal();
	public void signalAll();
}
