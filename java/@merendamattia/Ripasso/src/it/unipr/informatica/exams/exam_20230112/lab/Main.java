package it.unipr.informatica.exams.exam_20230112.lab;

public class Main {
	private void go() {
		int K = 10;
		AbstractFactory factory = AbstractFactory.getInstance();
		TemperatureSensor[] sensor = new TemperatureSensorImpl[K];
		TemperatureObserver[] observer = new TemperatureObserver[K];
		ExecutorService pool = Executors.getInstance();
		
		for(int i = 0; i < K; i++) {
			sensor[i] = factory.createSensor();
			observer[i] = factory.createObserver();
			
			sensor[i].attach(observer[i]);
			sensor[i].start();
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.err.println(e.getCause());
		}
		
		for(int i = 0; i < K; i++) {
			sensor[i].stop();
		}
		
		pool.shutdown();
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
}
