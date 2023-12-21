package it.unipr.informatica.creationals.abstract_factory;

public class ConcreteAbstractFactory implements AbstractFactory {
	
	// Singleton
	private static ConcreteAbstractFactory instance = null;
	public static ConcreteAbstractFactory newInstance() { 
		if(instance == null) {
			synchronized (ConcreteAbstractFactory.class) {
				if(instance == null)
					instance = new ConcreteAbstractFactory();
			}
		}
		return instance; 
	}
	private ConcreteAbstractFactory() { }
	// ! Singleton
	
	@Override
	public Animale createCane() {
		return new Cane();
	}

	@Override
	public Animale createGatto() {
		return new Gatto();
	}

	@Override
	public Animale createCavallo() {
		return new Cavallo();
	}

} // ! ConcreteAbstractFactory
