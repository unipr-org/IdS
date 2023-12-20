package it.unipr.informatica.design_patterns.factory_method.maps.concrete;

import it.unipr.informatica.design_patterns.factory_method.maps.abstracts.ElementHandler;
import it.unipr.informatica.design_patterns.factory_method.maps.abstracts.MapElement;

public class ConnectorHandler extends ElementHandler {

	@Override
	protected MapElement newElement() {
		return new Connector();
	}

	public void connect(Connector connector, Place from, Place to) {
		connector.connectPlaces(from, to);
	}
}
