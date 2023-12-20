package it.unipr.informatica.exercises.esercizio03;

public interface Condition {
	public void await() throws InterruptedException;
	public void signal();
	public void signalAll();
}
