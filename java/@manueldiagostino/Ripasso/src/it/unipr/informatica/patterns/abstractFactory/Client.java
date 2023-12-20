package it.unipr.informatica.patterns.abstractFactory;

import java.util.LinkedList;
import java.util.List;

import it.unipr.informatica.patterns.abstractFactory.abstractions.*;
/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 * 
 * Client knows the AbstractFactory interface and
 * 
 */
public class Client {
	private AbstractAnimalsFactory factory;
	private Animal[] animals = new Animal[3];
	
	public Client(AbstractAnimalsFactory factory) {
		this.factory = factory;
	}
	
	public void createZoo() {
		animals[0] = factory.createLandAnimal();
		animals[1] = factory.createSkyAnimal();
		animals[2] = factory.createWaterAnimal();
	}
	
	public void printZoo() {
		if (animals[0] == null)
			return;
		
		for (Animal a : animals)
			System.out.println("I'm " + a.getName() + " " + a.sound());
	}
}
