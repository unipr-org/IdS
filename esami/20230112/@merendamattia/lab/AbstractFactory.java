package it.unipr.informatica.exams.exam_20230112.lab;

public class AbstractFactory {
	// Singleton
	private static AbstractFactory INSTANCE = null;
	public static AbstractFactory getInstance() {
		if(INSTANCE == null) {
			synchronized (AbstractFactory.class) {
				if(INSTANCE == null) 
					INSTANCE = new AbstractFactory();
			}
		}
		return INSTANCE;
	}
	private AbstractFactory() { }
	// ! Singleton
	
	private static int NUM_SENSORS = 0;
	private static int NUM_OBSERVER = 0;
	
	public TemperatureSensor createSensor() {
		return new TemperatureSensorImpl(NUM_SENSORS++);
	}
	
	public TemperatureObserver createObserver() {
		return new TemperatureObserverImpl(NUM_OBSERVER++);
	}
	
} // ! AbstractFactory
