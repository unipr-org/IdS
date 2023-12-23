package it.unipr.informatica.design_patterns.exercises.delivery.abstracts;

public interface DeliveryBuilder {
	public void addDestination(String dest);
	public void addSource(String source);
	public void addTransportObserver();
	public void addTransport(Transport transport);
	public Delivery getDelivery();
}
