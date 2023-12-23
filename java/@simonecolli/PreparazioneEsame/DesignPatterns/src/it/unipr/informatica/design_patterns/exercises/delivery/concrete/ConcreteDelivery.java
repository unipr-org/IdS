package it.unipr.informatica.design_patterns.exercises.delivery.concrete;

import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.Delivery;
import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.Transport;
import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.TransportObserver;

public class ConcreteDelivery implements Delivery{

	private String status_;
	private String destination_;
	private String source_;
	private Transport transport_;
	private TransportObserver observer_;
	
	public ConcreteDelivery() {
		status_ = "unknown";
		destination_ = "unknown";
		source_ = "unknown";
		transport_ = null;
		observer_ = new DeliveryTracker();
	}

	@Override
	public String getStatus() {
		return status_;
	}

	@Override
	public String getDestination() {
		return destination_;
	}

	@Override
	public String getSource() {
		return source_;
	}
	
	public void setSource(String source) {
		this.source_ = source;
	}

	public void setDestination(String dest) {
		this.destination_ = dest;
	}
	public void setTransport(Transport transport) {
		this.transport_ = transport;
	}
	public void createTransportObserver() {
		if(transport_ == null)
			throw new IllegalMonitorStateException("transport_ == null");
	
		this.transport_.addObserver(observer_);
	}
	
	private class DeliveryTracker implements TransportObserver {

		@Override
		public void update(String status) {
			System.out.println("status changed: " + status_ + " -> " + status);
			status_ = status;
		}
		
	}

}
