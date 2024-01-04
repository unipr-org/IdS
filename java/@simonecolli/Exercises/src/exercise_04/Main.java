package exercise_04;

import exercise_04.abstracts.Launcher;
import exercise_04.abstracts.Monitor;
import exercise_04.abstracts.MonitorSet;
import exercise_04.concretes.LauncherImpl;
import exercise_04.concretes.MonitorSetImpl;

public class Main {
	public static void work() {
		int k = (int)(Math.random()*1000);
		try {
			Thread.sleep(k + 2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("k = " + k );
	}
	
	public static void main(String[] args) {
		MonitorSet ms = new MonitorSetImpl();
		Launcher launcher = new LauncherImpl();
		
		for (int i=0; i < 100; ++i) {
			
			Runnable r = () -> {
				work();
			};
			
			Monitor m = launcher.launch(r);
			ms.add(m);
		}
		
		try {
			while(!ms.await()) {
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Main terminated");
	}
}
