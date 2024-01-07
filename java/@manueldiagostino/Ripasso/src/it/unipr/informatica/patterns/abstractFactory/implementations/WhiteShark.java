package it.unipr.informatica.patterns.abstractFactory.implementations;

import it.unipr.informatica.patterns.abstractFactory.abstractions.Animal;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class WhiteShark implements Animal {
	private String name;

	public WhiteShark(String name) {
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
