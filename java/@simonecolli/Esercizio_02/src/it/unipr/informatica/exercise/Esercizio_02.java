package it.unipr.informatica.exercise;

public class Esercizio_02 {
	public static void main(String[] args) {
		int numberOfTasks = 10;
		Task[] tasks = new Task[numberOfTasks];
		
		for (int i = 0; i < numberOfTasks; ++i) {

			int tmp = i;
			tasks[i] = new Task() {	
				@Override
				public void perform() {
					System.out.println("Task " + tmp + " started");
					try {
						Thread.sleep((int)(100 + 1 * Math.random()));
					} catch (InterruptedException e) {
						e.printStackTrace();
						return;
					}
					
					System.out.println("Task " + tmp + " terminated");
				}
			};
		}
		Launcher launcher = new LauncherImplStartIndipendent();
		launcher.start(tasks);
		
		System.out.println("==================== Start returned ====================");
	}
}
