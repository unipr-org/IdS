package it.unipr.informatica.patterns.abstractFactory.implementations;

import it.unipr.informatica.patterns.abstractFactory.abstractions.AbstractAnimalsFactory;
import it.unipr.informatica.patterns.abstractFactory.abstractions.Animal;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class ExoticAnimalsFactory implements AbstractAnimalsFactory {

	@Override
	public Animal createSkyAnimal() {
		return new Eagle("Eagle Ercules");
	}

	@Override
	public Animal createWaterAnimal() {
		return new WhiteShark("WhiteShark WilliamShakespeare");
	}

	@Override
	public Animal createLandAnimal() {
		return new Tiger("Tiger Timothée");
	}

}
