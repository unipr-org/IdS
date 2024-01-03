package it.unipr.informatica.exam.Implementations;

import it.unipr.informatica.exam.Abstractions.TemperatureObserver;
import it.unipr.informatica.exam.Abstractions.TemperatureSensor;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class PrintObserver implements TemperatureObserver {

	@Override
	public void update(TemperatureSensor sensor) {
		System.out.println("[" + sensor.getID() + "] " + sensor.getTemperature());
	}
	
}
