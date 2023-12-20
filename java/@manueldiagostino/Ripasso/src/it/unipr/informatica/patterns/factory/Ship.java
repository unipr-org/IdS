package it.unipr.informatica.patterns.factory;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Ship implements Transport {
	private String name;
	private int weight;
	
	public Ship() {
		name = "Ship";
		weight = 800;
	}
	
	@Override
	public String deliver() {
		return "Delivering: [" + name + "," + weight + "]";
	}
}
