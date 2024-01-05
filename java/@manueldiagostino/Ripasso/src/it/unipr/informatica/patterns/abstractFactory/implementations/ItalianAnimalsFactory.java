package it.unipr.informatica.patterns.abstractFactory.implementations;

import it.unipr.informatica.patterns.abstractFactory.abstractions.AbstractAnimalsFactory;
import it.unipr.informatica.patterns.abstractFactory.abstractions.Animal;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class ItalianAnimalsFactory implements AbstractAnimalsFactory {

	@Override
	public Animal createSkyAnimal() {
		return new Parrow("Parrow Patrick");
	}

	@Override
	public Animal createWaterAnimal() {
		return new Sardine("Sardine Susy");
	}

	@Override
	public Animal createLandAnimal() {
		return new Sheep("Sheep Susanne");
	}
}
