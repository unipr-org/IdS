package it.unipr.informatica.exam;

import it.unipr.informatica.exam.Abstractions.TemperatureObserver;
import it.unipr.informatica.exam.Abstractions.TemperatureSensor;
import it.unipr.informatica.exam.Implementations.PrintObserver;
import it.unipr.informatica.exam.Implementations.TemperatureSensorImpl;
import it.unipr.informatica.exam.factory.AbstractFactory;
import it.unipr.informatica.exam.factory.AbstractFactoryImpl;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Main {
	public static void main(String[] args) {
		int k = 10;
		AbstractFactory factory = new AbstractFactoryImpl();
		TemperatureSensor[] sensors = new TemperatureSensorImpl[k];
		
		for (int i=0; i<k; ++i) {
			sensors[i] = factory.createUniqueTemperatureSensor();
			TemperatureObserver o = new PrintObserver();
			
			sensors[i].attach(o);
			sensors[i].start();
		}
		
		try {
			System.out.println("Main sleeping");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i=0; i<k; ++i) {
			sensors[i].stop();
		}
		
		System.out.println("Main returned");
	}
}
