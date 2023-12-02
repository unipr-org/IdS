package it.unipr.informatica.exercise;

public class Exercise02 {
	public static void main(String[] args) {
		Task[] activities = new Task[10];
		
		for (int i=0; i<activities.length; ++i) {
			int id = i;
		
			activities[id] = new Task() {
				@Override
				public void perform() {
					System.out.println("run() " + id + " entered");
					
					
					try {
						Thread.sleep(1500 + (int)(1600*Math.random()));
					} catch (InterruptedException e) {
						System.err.println("run() " + id + " interrupted");
					}
					System.out.println("run() " + id + " terminated");
				}
			};
		}
		
		new LauncherImpl().start(activities);
	}
}
