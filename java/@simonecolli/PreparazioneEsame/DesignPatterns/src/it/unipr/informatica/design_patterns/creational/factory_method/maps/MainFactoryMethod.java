package it.unipr.informatica.design_patterns.creational.factory_method.maps;

import java.io.IOException;

import it.unipr.informatica.design_patterns.creational.factory_method.maps.concrete.*;

public class MainFactoryMethod {
	public static void main(String[] args) throws IOException {
		ConnectorHandler connectorHandler = new ConnectorHandler();
		PlaceHandler placeHandler = new PlaceHandler();
		
		Place from, to;
		Connector connector;
		
		System.out.println("Creating place 1");
		from = (Place) placeHandler.createElement();
		
		System.out.println("Creating place 2");
		to = (Place) placeHandler.createElement();
		
		
		System.out.println("Creating connector");
		connector = (Connector) connectorHandler.createElement();
		connectorHandler.connect(connector, from, to);
		
		placeHandler.paintElement(from);
		placeHandler.paintElement(to);
		connectorHandler.paintElement(connector);
		
	}
}
