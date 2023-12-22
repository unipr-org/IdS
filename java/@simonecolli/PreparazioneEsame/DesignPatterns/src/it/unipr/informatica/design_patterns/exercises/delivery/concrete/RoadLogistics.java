package it.unipr.informatica.design_patterns.exercises.delivery.concrete;

import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.Delivery;
import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.DeliveryBuilder;
import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.Logistics;
import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.Transport;

public class RoadLogistics implements Logistics{
	
	private Delivery delivery_;
	private Transport truck_;
	
	public RoadLogistics() {
		delivery_ = null;
		truck_ = null;
	}
	
	@Override
	public void planDelivery(String source, String destination) {
		DeliveryBuilder db = new ConcreteDeliveryBuilder();
		db.addSource(source);
		db.addDestination(destination);
		db.addTransport(truck_);
		db.addTransportObserver();
		this.delivery_ = db.getDelivery();
	}

	@Override
	public void startDelivery() {
		if(delivery_ == null)
			throw new IllegalMonitorStateException("delivery_ == null");

		if(truck_ == null)
			throw new IllegalMonitorStateException("truck_ == null");
		
		truck_.deliver();
	}

	@Override
	public void createTransport() {
		truck_ = new Truck();
	}

	@Override
	public Delivery getDelivery() {
		return delivery_;
	}

	@Override
	public Transport getTransport() {
		return truck_;
	}

}
