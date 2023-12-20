package it.unipr.informatica.locks;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Condition {
	public void await() throws InterruptedException;
	public void signal() throws InterruptedException;
	public void signalAll() throws InterruptedException;
}
