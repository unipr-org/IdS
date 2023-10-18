package it.unipr.informatica.exercise;

public interface MonitorSet {
	public boolean add(Monitor m);
	
	public void await() throws InterruptedException;
}
