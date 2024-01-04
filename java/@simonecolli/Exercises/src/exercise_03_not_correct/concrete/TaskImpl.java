package exercise_03_not_correct.concrete;

import exercise_03_not_correct.abstracts.Task;

public class TaskImpl implements Task {
	
	private int id_;
	
	public TaskImpl(int id) {
		id_ = id;
	}
	
	@Override
	public void run(Object startMutex) {
		
		System.out.println("task " + id_ + " preparing");
		
		try {
			Thread.sleep((int) (Math.random() * 10));
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		System.out.println("task " + id_ + " wait");
		
		synchronized (startMutex) {
			startMutex.notify();
			System.out.println("task " + id_ + " notify");
		}

		System.out.println("task " + id_ + " start");
		
		try {
			Thread.sleep((int) (Math.random() * 2000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};


		System.out.println("task " + id_ + " done");
	}

}
