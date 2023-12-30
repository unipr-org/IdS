package it.unipr.informatica.exercise.esercizio4;

public class Main {
	private int N = 100;
	
	private void go() {
		Launcher l = new LauncherImpl();
		MonitorSet ms = new MonitorSetImpl();
		
		for(int i = 0; i < N; i++) {
			int id = i;
			
			Runnable r = () -> {
				try {
					int time = 100 + (int) (Math.random() * 100);
					
					System.out.printf("[Runnable%s] time %s - %s%n", id, time, Thread.currentThread());
					
					Thread.sleep(time);
					
				} catch (InterruptedException e) {
					System.err.println(e.getCause());
				}
			};
			
			ms.add(l.launch(r, i));
			
		}
		
		try {
			ms.await();
		} catch (InterruptedException e) {
			System.err.println(e.getCause());
		}
		
	} // ! go()
	
	private void goAll() {
		Launcher l = new LauncherImpl();
		MonitorSet ms = new MonitorSetImpl();
		
		for(int i = 0; i < N; i++) {
			int id = i;
			
			Runnable r = () -> {
				try {
					int time = 100 + (int) (Math.random() * 100);
					
					System.out.printf("[Runnable%s] time %s - %s%n", id, time, Thread.currentThread());
					
					Thread.sleep(time);
					
				} catch (InterruptedException e) {
					System.err.println(e.getCause());
				}
			};
			
			ms.add(l.launch(r, i));
			
		}
		
		try {
			ms.awaitAll();
		} catch (InterruptedException e) {
			System.err.println(e.getCause());
		}
		
	} // ! goAll()
	
	public static void main(String[] args) {
		System.out.println("\t\t******* go() ******* ");
		new Main().go();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.err.println(e.getCause());
		}
		
		System.out.println("\n\n\t\t******* goAll() ******* ");
		new Main().goAll();
	}
} // ! Main
