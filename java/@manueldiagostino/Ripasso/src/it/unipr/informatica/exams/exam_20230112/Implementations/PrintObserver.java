package it.unipr.informatica.exams.exam_20230112.Implementations;

import it.unipr.informatica.exams.exam_20230112.Abstractions.TemperatureObserver;
import it.unipr.informatica.exams.exam_20230112.Abstractions.TemperatureSensor;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class PrintObserver implements TemperatureObserver {

	@Override
	public void update(TemperatureSensor sensor) {
		System.out.println("[" + sensor.getID() + "] " + sensor.getTemperature());
	}

}
