package it.unipr.informatica.exercise.esercizio2;

public class Exercise02 {
	public static void main(String[] args) {
		Task[] tasks = new Task[10];
		
		for(int i = 0; i < tasks.length; ++i) {
			int id = i;
			
			tasks[i] = new Task() {
				@Override
				public void perform() {
					System.out.println("Task " + id + " started");
					
					try {
						int time = (int) (500 + 1000 * Math.random());
						Thread.sleep(time);
					} catch (Exception e) {
						return;
					}
					
					System.out.println("Task " + id + " terminated");
				}
			};
		}
		
		Launcher launcher = new LauncherImpl();
		
		launcher.start(tasks);
		
		System.out.println("Started returned");
	}
}
