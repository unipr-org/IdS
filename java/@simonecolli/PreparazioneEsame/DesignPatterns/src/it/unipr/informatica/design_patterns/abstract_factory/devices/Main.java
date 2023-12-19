package it.unipr.informatica.design_patterns.abstract_factory.devices;

import it.unipr.informatica.design_patterns.abstract_factory.devices.implementations.CdDeviceFactory;
import it.unipr.informatica.design_patterns.abstract_factory.devices.implementations.TapeDeviceFactory;

public class Main {
	public static void main(String[] args) {
		Client client = new Client();
		 System.out.println("**testing tape**");
		 client.selectTecnology(new TapeDeviceFactory());
		 client.test("Prova di scrittura su tape");
		 

		 System.out.println("**testing CD**");
		 client.selectTecnology(new CdDeviceFactory());
		 client.test("Prova di scrittura su cd");
	}
}
