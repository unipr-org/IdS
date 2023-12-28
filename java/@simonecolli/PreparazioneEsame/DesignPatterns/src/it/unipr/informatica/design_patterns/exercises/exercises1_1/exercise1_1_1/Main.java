package it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1;

import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.abstracts.Component;
import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.concrete.Country;
import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.concrete.District;
import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.concrete.Leaf;
import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.concrete.Region;

public class Main {
	public static void main(String[] args) {
		Component map = new Country("Italy");
		Component Veneto = new Region("Veneto");
		Component Emilia = new Region("Emilia Romagna");
		
		Component Padova = new District("Padova");
		Component Vicenza = new District("Vicenza");
		Component Parma = new District("Parma");
		Component Piacenza = new District("Piacenza");
		
		Component Mestrino = new Leaf("Mestrino");
		Component Veggiano = new Leaf("Veggiano");
		
		Component Fidenza = new Leaf("Fidenza");
		Component Salso = new Leaf("Salsomaggiore Terme");
		Component Fiorenzuola = new Leaf("Fiorenzuola");
		
		try {
			Salso.add(Fiorenzuola);
		} catch (Exception e) {
			System.err.println(e);
		}
		
		Padova.add(Mestrino);
		Padova.add(Veggiano);
		Parma.add(Fidenza);
		Parma.add(Salso);
		Piacenza.add(Fiorenzuola);
		
		try {
			Parma.add(Piacenza);
		} catch (Exception e) {
			System.err.println(e);
		}
		
		Veneto.add(Padova);
		Veneto.add(Vicenza);
		Emilia.add(Parma);
		Emilia.add(Piacenza);
		
		try {
			map.add(Fiorenzuola);
		} catch (Exception e) {
			System.err.println(e);
		}
		map.add(Emilia);
		map.add(Veneto);
		System.out.println(map.getDescription());
		Veneto.remove(Vicenza);
		System.out.println(map.getDescription());
	}
}
