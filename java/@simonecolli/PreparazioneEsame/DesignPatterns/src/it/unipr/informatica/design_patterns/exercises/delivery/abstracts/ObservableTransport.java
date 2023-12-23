package it.unipr.informatica.design_patterns.exercises.delivery.abstracts;

public interface ObservableTransport {
	public void addObserver(TransportObserver observer);
	public void removeObserver(TransportObserver observer);
	public void notifyObservers();
}
