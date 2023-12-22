package it.unipr.informatica.design_patterns.exercises.delivery.abstracts;

public interface Transport extends ObservableTransport{
	public void deliver();
	public void completeDeliver();
	public String getInfos();
}
