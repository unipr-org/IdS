package it.unipr.informatica.exams.exam_20230112.Abstractions;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public interface TemperatureSensor {
	public int getID();

	public double getTemperature();

	public void start();

	public void stop();

	public void attach(TemperatureObserver o);

	public void detach(TemperatureObserver o);
}
