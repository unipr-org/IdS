package it.unipr.informatica.design_patterns.exercises.delivery.abstracts;

public interface Logistics {
	public void planDelivery(String source, String destination);
	public void startDelivery();
	public void createTransport();
	public Delivery getDelivery();
	public Transport getTransport();
}
