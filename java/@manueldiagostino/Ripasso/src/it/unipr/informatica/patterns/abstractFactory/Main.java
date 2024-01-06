package it.unipr.informatica.patterns.abstractFactory;

import it.unipr.informatica.patterns.abstractFactory.implementations.ExoticAnimalsFactory;
import it.unipr.informatica.patterns.abstractFactory.implementations.ItalianAnimalsFactory;
import it.unipr.informatica.patterns.abstractFactory.implementations.SingletonItalianAnimalsFactory;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class Main {
	public static void main(String[] args) {
		Client c1 = new Client(SingletonItalianAnimalsFactory.getInstance());
		Client c2 = new Client(new ExoticAnimalsFactory());

		c1.createZoo();
		c2.createZoo();

		c1.printZoo();
		c2.printZoo();
	}
}
