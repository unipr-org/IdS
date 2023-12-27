package it.unipr.informatica.exams.exam_20230112;

public class TemperatureObserverImpl implements TemperatureObserver {
	private int ID;
	
	public TemperatureObserverImpl(int ID) {
		this.ID = ID;
	}
	
	@Override
	public void update(TemperatureSensor s) {
		System.out.println("Observer" + ID + " notificato: " + s);	
	}
} // ! TemperatureObserverImpl
