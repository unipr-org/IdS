package it.unipr.informatica.exams.exam_20230112;

import it.unipr.informatica.exams.exam_20230112.Abstractions.TemperatureObserver;
import it.unipr.informatica.exams.exam_20230112.Abstractions.TemperatureSensor;
import it.unipr.informatica.exams.exam_20230112.Implementations.PrintObserver;
import it.unipr.informatica.exams.exam_20230112.Implementations.TemperatureSensorImpl;
import it.unipr.informatica.exams.exam_20230112.factory.AbstractFactory;
import it.unipr.informatica.exams.exam_20230112.factory.AbstractFactoryImpl;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class Main {
	public static void main(String[] args) {
		int k = 10;
		AbstractFactory factory = new AbstractFactoryImpl();
		TemperatureSensor[] sensors = new TemperatureSensorImpl[k];

		for (int i = 0; i < k; ++i) {
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

		for (int i = 0; i < k; ++i) {
			sensors[i].stop();
		}

		System.out.println("Main returned");
	}
}
