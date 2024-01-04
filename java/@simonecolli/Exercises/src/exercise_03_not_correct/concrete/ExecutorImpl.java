package exercise_03_not_correct.concrete;

import exercise_03_not_correct.abstracts.Executor;
import exercise_03_not_correct.abstracts.Task;

public class ExecutorImpl implements Executor{
	private Object mutex_;
	public ExecutorImpl() {
		mutex_ = new Object();
	}
	
	@Override
	public void launch(Task[] tasks) {
		
		Object counter = new Object();
		
		synchronized (counter) {
			
			synchronized(mutex_) {
				
				for(int i = 0; i < tasks.length; ++i) {
					int tmp = i;
					
					new Thread(()-> {
						synchronized (mutex_) {
							tasks[tmp].run(counter);						
						}
					}).start();
				
				}
			}

			try {
				int tasksNum = tasks.length;
				while(tasksNum > 0) {
					System.out.println("missing: " + tasksNum + " tasks");
					counter.wait();
					--tasksNum;
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("quitting launch");
	}

	
}
