package it.unipr.informatica.exercises.esercizio03;

public class Main {
	public void go() {
		int numTasks = 20;
		
		Executor ex = new ExecutorImpl();
		
		Task[] tasks = new Task[numTasks];
		
		for(int i = 0; i < numTasks; i++) {
			int id = i;
			
			tasks[i] = new Task() {
				
				@Override
				public void run(Object startedMutex) {
					
					try {
						
						Thread.sleep(100 + (int) (Math.random() * 1000));
						// System.out.println("Task" + id + "\t Preparation terminated");
						
						synchronized (startedMutex) {
							startedMutex.notify();
						}
						
						System.out.println("Task" + id + "\t Preparation terminated signaled");
						
						Thread.sleep(3000 + (int) (Math.random() * 1000));
						System.out.println("Task" + id + "\t Execution terminated");
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}	
			};
		}
		
		ex.launch(tasks);
		
		System.out.println("Executor returned");
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
}
