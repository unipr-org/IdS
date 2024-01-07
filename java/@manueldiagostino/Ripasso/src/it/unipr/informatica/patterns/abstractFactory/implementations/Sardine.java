package it.unipr.informatica.patterns.abstractFactory.implementations;

import it.unipr.informatica.patterns.abstractFactory.abstractions.Animal;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class Sardine implements Animal {
	private String name;

	public Sardine(String name) {
		this.name = name;
	}

	@Override
	public Type getType() {
		return Animal.Type.WATER;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String sound() {
		// TODO Auto-generated method stub
		return "...";
	}

}
