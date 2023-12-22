package it.unipr.informatica.design_patterns.exercises.delivery;

import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.Logistics;
import it.unipr.informatica.design_patterns.exercises.delivery.abstracts.Transport;
import it.unipr.informatica.design_patterns.exercises.delivery.concrete.RoadLogistics;

public class Client {
	public static void main(String[] args) {
		Logistics brt = new RoadLogistics();
		brt.createTransport();
		brt.planDelivery("Rome", "Campus Unipr");
		
		System.out.println("current delivery status: " + brt.getDelivery().getStatus());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		brt.startDelivery();

		System.out.println("");
		System.out.println("current delivery status: " + brt.getDelivery().getStatus());
		
		Transport currentTransport = brt.getTransport();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		currentTransport.completeDeliver();
		System.out.println("");
		System.out.println("current delivery status: " + brt.getDelivery().getStatus());
		System.out.println("deliver infos: " + brt.getDelivery().getSource() + " -> " + brt.getDelivery().getDestination());
	}
}
