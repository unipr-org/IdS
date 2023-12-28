package exam_2023_01_12.lab.it.unipr.informatica.exam;

import exam_2023_01_12.lab.it.unipr.informatica.exam.abstracts.TemperatureObserver;
import exam_2023_01_12.lab.it.unipr.informatica.exam.abstracts.TemperatureSensor;
import exam_2023_01_12.lab.it.unipr.informatica.exam.abstracts.TemperatureSensorAbstractFactory;
import exam_2023_01_12.lab.it.unipr.informatica.exam.concretes.TemperatureSensorFactory;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Enviroment e = Enviroment.getInstance();
		
		TemperatureSensorAbstractFactory factory = new TemperatureSensorFactory();
		TemperatureSensor sensor1 = factory.makeTemperatureSensor();
		TemperatureSensor sensor2 = factory.makeTemperatureSensor();
		
		sensor1.attach(new TemperatureObserver() {
			@Override
			public void update(TemperatureSensor s) {
				System.out.println("sensor " + s.getID() + ": " + s.getTemperature());
			}
		});

		sensor2.attach(new TemperatureObserver() {
			@Override
			public void update(TemperatureSensor s) {
				System.out.println("sensor " + s.getID() + ": " + s.getTemperature());
			}
		});
		
		sensor1.start();
		sensor2.start();
		
		Thread.sleep(1000);
		e.updateTemperature(10.5d);
		Thread.sleep(1000);
		e.updateTemperature(5.5d);
		Thread.sleep(1000);
		e.updateTemperature(7.5d);
		Thread.sleep(2000);
		e.updateTemperature(6.5d);
		
		Thread.sleep(5000);
		sensor1.stop();
		sensor2.stop();
		System.out.println("end");
	}
}
