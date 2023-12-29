package it.unipr.informatica.exercise.abstractions;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface MonitorSet {
	public boolean add(Monitor m);
	public void await() throws InterruptedException;
}
