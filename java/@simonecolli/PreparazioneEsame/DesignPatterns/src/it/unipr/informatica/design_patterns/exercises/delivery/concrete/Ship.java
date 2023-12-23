package it.unipr.informatica.design_patterns.exercises.delivery.concrete;

import java.util.Vector;

import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.Transport;
import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.TransportObserver;

public class Ship implements Transport{
	
	private String status_;
	private Vector<TransportObserver> observers_;
	
	public Ship() {
		this.status_ = "ready";
		this.observers_ = new Vector<>();
	}
	@Override
	public void addObserver(TransportObserver observer) {
		observers_.add(observer);
	}

	@Override
	public void removeObserver(TransportObserver observer) {
		observers_.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (TransportObserver observer : observers_)
			observer.update(status_);
		
	}

	@Override
	public void deliver() {
		this.status_ = "deliver started";
		notifyObservers();
	}

	@Override
	public void completeDeliver() {
		this.status_ = "deliver completed";
		notifyObservers();
	}

	@Override
	public String getInfos() {
		return "this is a ship";
	}

}
