package it.unipr.informatica.patterns.abstractFactory.implementations;

import it.unipr.informatica.patterns.abstractFactory.abstractions.Animal;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class Tiger implements Animal {
	private String name;

	public Tiger(String name) {
		this.name = name;
	}

	@Override
	public Animal.Type getType() {
		return Animal.Type.LAND;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String sound() {
		return "ROOOAAR";
	}

}
