package it.unipr.informatica.example;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Main {
	public void go() {
		Task[] tasks = new Task[5];
		
		for (int i=0; i<tasks.length; ++i) {
			int id = i;
			tasks[id] = new Task() {
				
				@Override
				public void run(Object mutex) {
					System.out.println("Task " + id + " entered");
					try {
						Thread.sleep(200 + (int)(200*Math.random()));
						synchronized (mutex) {
							mutex.notify();							
						}
						System.out.println("Task " + id + " notified");
						
						Thread.sleep(2000 + (int)(2000*Math.random()));
						System.out.println("Task " + id + " terminated");		
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			};
		}
		
		new ExecutorImpl().launch(tasks);
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
}
