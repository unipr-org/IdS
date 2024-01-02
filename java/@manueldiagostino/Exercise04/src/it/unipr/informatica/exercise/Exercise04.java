package it.unipr.informatica.exercise;

import it.unipr.informatica.exercise.abstractions.Monitor;
import it.unipr.informatica.exercise.abstractions.MonitorSet;
import it.unipr.informatica.exercise.abstractions.Launcher;
import it.unipr.informatica.exercise.implementations.LauncherImpl;
import it.unipr.informatica.exercise.implementations.MonitorImpl;
import it.unipr.informatica.exercise.implementations.MonitorSetImpl;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Exercise04 {
	public static void work() {
		int k = (int)(Math.random()*100);
		try {
			Thread.sleep(2500 + 10*k);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("[" + k + "]");
	}
	
	public static void main(String[] args) throws InterruptedException {
		MonitorSet ms = new MonitorSetImpl();
		Launcher l = new LauncherImpl();
		
		for (int i=0; i<100; ++i) {
			
			Runnable runnable = () -> {
				work();
			};
			
			ms.add(l.launch(runnable));
		}
		
		ms.await();
		System.out.println("Main terminated");
	}
}
