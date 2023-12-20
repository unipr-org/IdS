package it.unipr.informatica.locks;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Lock {
	public void lock();
	public void unlock();
	public Condition NewCondition();
}
