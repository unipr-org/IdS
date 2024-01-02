package it.unipr.informatica.exercise.esercizio4;

public interface MonitorSet {
	public boolean add(MonitorImpl m);
	public void await() throws InterruptedException;
	public void awaitAll() throws InterruptedException;
}
