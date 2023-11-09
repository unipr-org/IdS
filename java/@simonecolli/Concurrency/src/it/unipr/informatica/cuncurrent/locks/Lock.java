package it.unipr.informatica.cuncurrent.locks;


public interface Lock {
	public void lock();
	public void unlock();
	
	public Condition newCondition();
}
