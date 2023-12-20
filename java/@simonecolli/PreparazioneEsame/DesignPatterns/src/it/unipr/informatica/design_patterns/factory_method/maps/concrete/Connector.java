package it.unipr.informatica.design_patterns.factory_method.maps.concrete;

import it.unipr.informatica.design_patterns.factory_method.maps.abstracts.MapElement;

public class Connector implements MapElement {
	
	private String label_;
	private Place from_, to_;
	
	
	public Connector() {}
	
	
	public void connectPlaces(Place from, Place to) {
		from_ = from;
		to_ = to;
	}
	@Override
	public void setLabel(String label) {
		label_ = label;
	}
	@Override
	public String getPaintingData() {
		return label_ + " [" + from_.getPaintingData() + "] -> [" + to_.getPaintingData() +"]";
	}

}
