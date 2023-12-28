package exam_2023_01_12.lab.it.unipr.informatica.exam;

public class Enviroment {
	private volatile static Enviroment INSTANCE = null;
	private Object mutex_;
	private double currentTemperature_;
	private Enviroment() {
		currentTemperature_ = 0;
		mutex_ = new Object();
	}
	public double getCurrentTemperature() {
		synchronized (mutex_) {
			return currentTemperature_;
		}
	}
	public static Enviroment getInstance() {
		if(INSTANCE == null)
			synchronized (Enviroment.class) {
				if(INSTANCE == null)
					INSTANCE = new Enviroment();
			}
		return INSTANCE;
	}
	public void updateTemperature(double newTemperature) {
		synchronized (mutex_) {
			currentTemperature_ = newTemperature;
		}		
	}
}
