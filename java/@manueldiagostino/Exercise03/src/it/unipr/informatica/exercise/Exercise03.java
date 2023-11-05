package it.unipr.informatica.exercise;

public class Exercise03 {
	public static void main(String[] args) {
		Task[] activities = new Task[15];
		
		for (int i=0; i<activities.length; ++i) {
			int id = i;
		
			activities[id] = new Task() {
				@Override
				public void run(Object startedMutex) {
					System.out.println("run() " + id + " entered");
					
					synchronized (startedMutex) {
						System.out.println("run() " + id + " started");
						startedMutex.notifyAll();
					}
					
					try {
						Thread.sleep(1500 + (int)(1000*Math.random()));
					} catch (InterruptedException e) {
						System.err.println("run() " + id + " interrupted");
					}
					System.out.println("run() " + id + " terminated");
				}
			};
		}
		
		new ExecutorImpl().launch(activities);
	}
}
