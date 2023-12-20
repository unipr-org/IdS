package it.unipr.informatica.patterns.factory;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Truck implements Transport {
	private String name;
	private int weight;
	
	public Truck() {
		name = "Truck";
		weight = 20000;
	}
	
	@Override
	public String deliver() {
		return "Delivering: [" + name + "," + weight + "]";
	}
}
