package it.unipr.informatica.exercise;

public class LauncherImpl implements Launcher {
	@Override
	public void start(Task[] tasks) {
		if (tasks == null)
			throw new IllegalArgumentException("tasks is null");
		
		new ExecutionHandler(tasks);
		System.out.println("*** launch() exited ***");
	}
	
	// Classe privata per gestire l'esecuzione vera e propria
	private class ExecutionHandler {
		private final Object mutex_ = new Object(); // per l'accesso in mutua esclusione alla condition
		private OneReturned oneReturned = new OneReturned();
		
		private class OneReturned {
			boolean flag = false;
		}
		
		public ExecutionHandler(Task[] tasks) {
			int count_ = tasks.length;
			TaskHandler[] taskHandler = new TaskHandler[count_];
			
			// sfrutta oneReturned come una condition
			// su cui mettersi in attesa
			synchronized (oneReturned) {				
				for (int i=0; i<count_; ++i) {
					taskHandler[i] = new TaskHandler(i, tasks[i]);
					new Thread(taskHandler[i]).start();
				}
				
				try {
					oneReturned.wait();
				} catch (InterruptedException e) {
					System.err.println("ExecutionHandler interrupted");
				}
			}
		}
		
		private class TaskHandler implements Runnable {
			private final Task task_;
			private final int id_;
			
			public TaskHandler(int id, Task task) {
				task_ = task;
				id_ = id;
			}
			
			@Override
			public void run() {
				task_.perform();
				
				synchronized (ExecutionHandler.this.mutex_) {
					synchronized (ExecutionHandler.this.oneReturned) {
						
						if (ExecutionHandler.this.oneReturned.flag == true)
							return;
						
						ExecutionHandler.this.oneReturned.flag = true;
						ExecutionHandler.this.oneReturned.notify();
						
						System.out.println("Task [" + id_ + "]" + " notifies the ExecutionHandler");
					}
				}
			}
		}
	}
}
