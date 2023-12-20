package it.unipr.informatica.patterns.abstractFactory.abstractions;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface AbstractAnimalsFactory {
	public Animal createSkyAnimal();
	public Animal createWaterAnimal();
	public Animal createLandAnimal();
}
