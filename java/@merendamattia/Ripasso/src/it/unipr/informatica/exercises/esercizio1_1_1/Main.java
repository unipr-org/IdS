package it.unipr.informatica.exercises.esercizio1_1_1;

public class Main {
	private void go() {
		Component l1 = new Leaf("Soverato");
		Component l2 = new Leaf("Sellia Marina");
		Component l3 = new Leaf("Montepaone");
		Component l4 = new Leaf("Albinea");
		Component l5 = new Leaf("Rivalta");
		Component l6 = new Leaf("Reggiolo");
		
		Component d1 = new District("Catanzaro");
		Component d2 = new District("Reggio Emilia");
		
		Component r1 = new Region("Calabria");
		Component r2 = new Region("Emilia Romagna");
		
		Component c1 = new Country("Italia");
		
		d1.add(l1);
		d1.add(l2);
		d1.add(l3);
		r1.add(d1);
		c1.add(r1);
		
		d2.add(l4);
		d2.add(l5);
		d2.add(l6);
		r2.add(d2);
		c1.add(r2);
		
		c1.accept(new VisitorImpl());
		
		r2.remove(d2);
		
		System.out.println("\nRemoving " + d2 + "...\n");
		
		c1.accept(new VisitorImpl());
		
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
}
