package it.unipr.informatica.aspects;

import java.util.ArrayList;
import java.util.List;

import it.unipr.informatica.aspects.model.PersistentAspect;
import it.unipr.informatica.aspects.model.PersistentHandler;

public class PersistentAspectTest {
	private void printAndAddIntegers(List<Integer> list) {
		printIntegers(list);
		
		for(int i = 0; i < 3; ++i) 
			list.add((int) (Math.random() * 100));
	}
	
	private void printIntegers(List <Integer> list) {
		if (!list.isEmpty()) {
			System.out.println("Current integers:");
		
			for(Integer interi : list)
				System.out.println(interi);
		}
	}
	
	private void go() {
		try {
			PersistentHandler<ArrayList<Integer>> integerHandler = PersistentAspect.attach("src/it/unipr/informatica/aspects/backup/Integers.dat", new ArrayList<Integer>());
			
			List<Integer> interi = integerHandler.get();
		
			printAndAddIntegers(interi);
			
			integerHandler.commit();
			
			System.out.println("integers saved");
			
			printIntegers(interi);
			
			
		} catch(Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new PersistentAspectTest().go();
	}
}
