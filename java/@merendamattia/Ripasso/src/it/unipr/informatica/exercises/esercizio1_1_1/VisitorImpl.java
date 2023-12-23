package it.unipr.informatica.exercises.esercizio1_1_1;

public class VisitorImpl implements Visitor {

	@Override
	public void visit(Component item) {
		
		// Formattazione pigra
		
		if(item instanceof Country)
			System.out.println(item);
		
		if(item instanceof Region)
			System.out.println("\t" + item);
		
		if(item instanceof District)
			System.out.println("\t\t" + item);
		
		if(item instanceof Leaf)
			System.out.println("\t\t\t" + item);
	}

} // ! VisitorImpl
