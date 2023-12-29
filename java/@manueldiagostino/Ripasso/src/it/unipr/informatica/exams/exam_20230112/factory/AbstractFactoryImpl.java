package it.unipr.informatica.exams.exam_20230112.factory;

import it.unipr.informatica.exams.exam_20230112.Abstractions.TemperatureSensor;
import it.unipr.informatica.exams.exam_20230112.Implementations.TemperatureSensorImpl;

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
