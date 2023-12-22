package it.unipr.informatica.design_patterns.exercises.delivery.concrete;

import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.Delivery;
import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.DeliveryBuilder;
import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.Transport;

public class ConcreteDeliveryBuilder implements DeliveryBuilder{
	
	private ConcreteDelivery delivery_;
	public ConcreteDeliveryBuilder() {
		this.delivery_ = new ConcreteDelivery();
	}
	@Override
	public void addDestination(String dest) {
		delivery_.setDestination(dest);
	}

	@Override
	public void addSource(String source) {
		delivery_.setSource(source);
	}

	@Override
	public void addTransportObserver() {
		delivery_.createTransportObserver();
	}

	@Override
	public void addTransport(Transport transport) {
		delivery_.setTransport(transport);
	}

	@Override
	public Delivery getDelivery() {
		return delivery_;
	}

}
