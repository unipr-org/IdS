package it.unipr.informatica.design_patterns.factory_method.maps.abstracts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public abstract class ElementHandler {
	
	public MapElement createElement() throws IOException {
		System.out.println("Inserisci un label per l'elemento: ");
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in)
				);
		
		String label = reader.readLine();
		MapElement elem = newElement();
		elem.setLabel(label);
		return elem;
	}
//	protected così si lasciala la possiblità alle sottoclassi 
//	di "specializzarlo"
	protected abstract MapElement newElement();
	
	public void paintElement(MapElement element) {
		System.out.println(element.getPaintingData());
	};
}
