package it.unipr.informatica.exams.exam_20230112.factory;

import it.unipr.informatica.exams.exam_20230112.Abstractions.TemperatureSensor;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public abstract class AbstractFactory {
	public static int availableID = 0;

	public abstract TemperatureSensor createUniqueTemperatureSensor();
}
