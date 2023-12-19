package it.unipr.informatica.creationals.abstract_factory;

public class Main {
	private void go() {
		
		new Thread( () -> {
			AbstractFactory factory = ConcreteAbstractFactory.newInstance();
			Animale a = factory.createCane();
			a.verso();
			
			System.out.println(factory);
		}).start();
		
		new Thread( () -> {
			AbstractFactory factory = ConcreteAbstractFactory.newInstance();
			Animale a = factory.createGatto();
			a.verso();
			
			System.out.println(factory);
		}).start();
		
		new Thread( () -> {
			AbstractFactory factory = ConcreteAbstractFactory.newInstance();
			Animale a = factory.createCavallo();
			a.verso();
			
			System.out.println(factory);
		}).start();
		
		
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
}
