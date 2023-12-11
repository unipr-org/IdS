package it.unipr.informatica.locks;

public interface Lock {
	public void lock();
	public void unlock();
	public Condition NewCondition();
}
