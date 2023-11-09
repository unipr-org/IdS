package it.unipr.informatica.exercise;

public class Esercizio_03 {
	public static void main(String[] args) {
		Task[] tasks = new Task[5];
		
		for (int i = 0; i < 5; i++) {

			int tmp = i;
			tasks[i] = new Task() {	
				@Override
				public void run(Object o) {
					System.out.println("Task " + tmp + " started");
					o.notifyAll();
					
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
		Executor executor = new ExecutorImpl();
		executor.launch(tasks);
		
		System.out.println("Start returned");
	}
}
