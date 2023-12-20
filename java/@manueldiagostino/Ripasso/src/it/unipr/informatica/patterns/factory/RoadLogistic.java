package it.unipr.informatica.patterns.factory;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class RoadLogistic implements Logistics {

	@Override
	public Transport createLogistic() {
		return new Truck();
	}

}
