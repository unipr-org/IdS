package it.unipr.informatica.patterns.abstractFactory.implementations;

import it.unipr.informatica.patterns.abstractFactory.abstractions.Animal;
/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Parrow implements Animal {
	private String name;
	
	public Parrow(String name) {
		this.name = name;
	}
	
	@Override
	public Type getType() {
		return Animal.Type.SKY;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String sound() {
		return "CIP CIP";
	}

}
