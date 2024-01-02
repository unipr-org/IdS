package exam_2023_01_12.lab.it.unipr.informatica.exam.abstracts;

public interface TemperatureSensor {
	public int getID();
	public double getTemperature();
	public void start();
	public void stop();
	public void attach(TemperatureObserver o);
	public void detach(TemperatureObserver o);	
}
