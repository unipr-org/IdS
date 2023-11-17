package it.unipr.informatica.concurrent;

public interface Lock {
	public void lock();
	public void unlock();
	public Condition newCondition();
}
