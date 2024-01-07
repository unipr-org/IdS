package it.unipr.informatica.locks.model;

public interface Lock {
	public void lock();
	public void unlock();
	public Condition newCondition();
}
