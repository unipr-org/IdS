package it.unipr.informatica.exams.exam_20230207.lab;

import it.unipr.informatica.exams.exam_20230207.lab.abstractions.Actor;
import it.unipr.informatica.exams.exam_20230207.lab.abstractions.Resource;
import it.unipr.informatica.exams.exam_20230207.lab.factory.ActorResourceFactoryImpl;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		ActorResourceFactoryImpl factory = ActorResourceFactoryImpl.getInstance();

		Resource[] resources = factory.createResources(100);
		Actor[] actors = factory.createActors(7);

		for (Actor a : actors)
			a.start();

		Thread.sleep(10000);
		for (Actor a : actors)
			a.remove();
	}

}
