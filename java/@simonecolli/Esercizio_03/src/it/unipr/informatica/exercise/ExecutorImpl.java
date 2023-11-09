package it.unipr.informatica.exercise;

public class ExecutorImpl implements Executor {
	
//	due launcher causano attesa
//	private boolean flag_ = false;
	
	@Override
	public void launch(Task[] tasks) {
		
		if (tasks == null)
			throw new IllegalArgumentException("tasks");
		
		
		Object mutex = new Mutex();
		
		
		
		synchronized (mutex) {
			for (int i = 0; i < tasks.length; i++) {
				
				int tmp = i;
				Runnable runnable = new Runnable() {
					
					@Override
					public void run() {
						tasks[tmp].start();
						
	
					}
				};
				new Thread(runnable).start();
			}
		
			try {
				mutex.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
		
	}
	private class Mutex {
		
		private int size_;
		
		public Mutex(int size) {
			size_ = size;
		}
		
		
		public void notifyAll() {
			
		}
		public void wait() {
			
		}
	}

}
