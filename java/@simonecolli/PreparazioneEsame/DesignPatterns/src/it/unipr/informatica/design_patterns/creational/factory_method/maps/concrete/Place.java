package it.unipr.informatica.design_patterns.creational.factory_method.maps.concrete;

import it.unipr.informatica.design_patterns.creational.factory_method.maps.abstracts.MapElement;

public class Place implements MapElement {
	
	private String label_;
	public Place() {}
	
	@Override
	public void setLabel(String label) {
		label_ = label;
	}

	@Override
	public String getPaintingData() {
		return "city: " + label_;
	}
	
}
