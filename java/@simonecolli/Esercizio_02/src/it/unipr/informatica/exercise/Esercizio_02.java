package it.unipr.informatica.exercise;

public class Esercizio_02 {
	public static void main(String[] args) {
		Task[] tasks = new Task[5];
		
		for (int i = 0; i < 5; i++) {

			int tmp = i;
			tasks[i] = new Task() {	
				@Override
				public void perform() {
					System.out.println("Task " + tmp + " started");
					try {
						Thread.sleep((int)(500 + 1000 * Math.random()));
					} catch (InterruptedException e) {
						e.printStackTrace();
						return;
					}
					System.out.println("Task " + tmp + " terminated");
				}
			};
		}
		Launcher launcher = new LauncherImpl();
		launcher.start(tasks);
		
		System.out.println("Start returned");
	}
}
