package exercise_02.concretes;

import exercise_02.abstracts.Launcher;
import exercise_02.abstracts.Task;

public class LauncherImpl implements Launcher{

	private Object mutex_;
	public LauncherImpl() {
		mutex_ = new Object();
	}
	
	@Override
	public void start(Task[] tasks) {
		synchronized(mutex_) {
			for(int i = 0; i < tasks.length; ++i) {
				int tmp = i;
				new Thread(()-> {
					System.out.println("task " + tmp + " waiting");
					synchronized (mutex_) {
						tasks[tmp].perform();
						System.out.println("task " + tmp + " performed");
						System.out.println("task " + tmp + " notifies");
						mutex_.notify();
					}
				}).start();
			}
			
			try {
				mutex_.wait();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("quitting");
	}
	
}
