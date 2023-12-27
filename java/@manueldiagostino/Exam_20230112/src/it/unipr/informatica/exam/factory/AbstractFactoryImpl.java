package it.unipr.informatica.exam.factory;

import it.unipr.informatica.exam.Abstractions.TemperatureSensor;
import it.unipr.informatica.exam.Implementations.TemperatureSensorImpl;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class AbstractFactoryImpl extends AbstractFactory {
	@Override
	public TemperatureSensor createUniqueTemperatureSensor() {
		return new TemperatureSensorImpl(availableID++);
	}
}
