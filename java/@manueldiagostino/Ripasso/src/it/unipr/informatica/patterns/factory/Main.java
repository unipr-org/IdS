package it.unipr.informatica.patterns.factory;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Main {
	
	public void go() {
		List<Transport> transports = new LinkedList<Transport>();
		Logistics log1 = new RoadLogistic();
		Logistics log2 = new SeaLogistic();
		
		for (int i=0; i<30; ++i) {
			int n = (int)(Math.random()*11);
			
			if ((n%2) == 0)
				transports.add(log1.createLogistic());
			else
				transports.add(log2.createLogistic());
		}
		
		for (Transport t : transports)
			System.out.println(t.deliver());
			
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
}
