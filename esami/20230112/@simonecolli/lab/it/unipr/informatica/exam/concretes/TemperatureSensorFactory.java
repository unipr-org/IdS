package exam_2023_01_12.lab.it.unipr.informatica.exam.concretes;

import exam_2023_01_12.lab.it.unipr.informatica.exam.abstracts.TemperatureSensorAbstractFactory;
import exam_2023_01_12.lab.it.unipr.informatica.exam.abstracts.TemperatureSensor;

public class TemperatureSensorFactory implements TemperatureSensorAbstractFactory{
	
	private int id_;
	public TemperatureSensorFactory() {
		id_ = 1;
	}
	@Override
	public TemperatureSensor makeTemperatureSensor() {
		return new TemperatureSensorImpl(id_++);
	}
	
}
