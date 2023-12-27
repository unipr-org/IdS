package it.unipr.informatica.exam.factory;

import it.unipr.informatica.exam.Abstractions.TemperatureSensor;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public abstract class AbstractFactory {
	public static int availableID = 0;
	public abstract TemperatureSensor createUniqueTemperatureSensor();
}
