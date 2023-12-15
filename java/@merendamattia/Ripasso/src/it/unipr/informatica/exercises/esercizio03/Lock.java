package it.unipr.informatica.exercises.esercizio03;

public interface Lock {
	public void lock();
	public void unlock();
	public Condition newCondition();
}
