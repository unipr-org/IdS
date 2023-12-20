package it.unipr.informatica.patterns.abstractFactory.abstractions;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */


public interface Animal {
	public static enum Type {
		SKY,
		WATER,
		LAND
	}

	public Type getType();
	public String getName();
	public String sound();
}
