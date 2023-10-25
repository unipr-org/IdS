package it.unipr.informatica.exercise;

public class Exercise03 {
	public static void main(String[] args) {
		Task[] tasks = new Task[10];
		
		for(int i = 0; i < tasks.length; ++i) {
			int id = i;
			
			tasks[i] = (startedMutex) -> {
				if(startedMutex == null)
					throw new IllegalArgumentException("startedMutex");

				System.out.println("Task " + id + " started");
					
				synchronized (startedMutex) {
					startedMutex.notifyAll();
				}
				
				try {
					int time = (int) (500 + 1000 * Math.random());
					Thread.sleep(time);
				} catch (Exception e) {
					return;
				}
					
				System.out.println("Task " + id + " terminated");
			};
		}
		
		Executor executor = new ExecutorImpl();
		
		executor.launch(tasks);
		
		System.out.println("Started returned");
	}
}
