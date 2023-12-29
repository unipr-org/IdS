package it.unipr.informatica.exercise.abstractions;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Monitor {
	public void await() throws InterruptedException;
}
