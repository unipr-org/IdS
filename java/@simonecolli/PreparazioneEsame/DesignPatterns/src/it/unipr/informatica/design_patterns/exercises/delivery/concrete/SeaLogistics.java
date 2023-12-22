package it.unipr.informatica.design_patterns.exercises.delivery.concrete;

import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.Delivery;

import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.DeliveryBuilder;
import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.Logistics;
import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.Transport;

public class SeaLogistics implements Logistics{
	
	private Delivery delivery_;
	private Transport ship_;
	
	public SeaLogistics() {
		delivery_ = null;
		ship_ = null;
	}
	
	@Override
	public void planDelivery(String source, String destination) {
		DeliveryBuilder db = new ConcreteDeliveryBuilder();
		db.addSource(source);
		db.addDestination(destination);
		db.addTransport(ship_);
		db.addTransportObserver();
		this.delivery_ = db.getDelivery();
	}

	@Override
	public void startDelivery() {
		if(delivery_ == null)
			throw new IllegalMonitorStateException("delivery_ == null");

		if(ship_ == null)
			throw new IllegalMonitorStateException("truck_ == null");
		
		ship_.deliver();
	}

	@Override
	public void createTransport() {
		ship_ = new Ship();
	}
	
	@Override
	public Delivery getDelivery() {
		return delivery_;
	}
	@Override
	public Transport getTransport() {
		return ship_;
	}

}
