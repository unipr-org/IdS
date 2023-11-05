package it.unipr.informatica.exercise;

public class ExecutorImpl implements Executor {
	@Override
	public void launch(Task[] tasks) {
		if (tasks == null)
			throw new IllegalArgumentException("tasks is null");
		
		new ExecutionHandler(tasks);
		System.out.println("*** launch() exited ***");
	}
	
	// Classe privata per gestire l'esecuzione vera e propria
	private class ExecutionHandler {
		private final Object mutex_ = new Object(); // accesso a count in mutua esclusione
		private int count_;
		
		public ExecutionHandler(Task[] tasks) {
			count_ = tasks.length;
			TaskHandler[] taskHandler = new TaskHandler[count_];
			
			// prende il mutex così che ExecutionHandler.mutex.wait()
			// avvenga prima di qualsiasi ExecutionHandler.mutex.notify()
			synchronized (mutex_) {				
				for (int i=0; i<count_; ++i) {
					taskHandler[i] = new TaskHandler(i, tasks[i]);
					new Thread(taskHandler[i]).start();
				}
				
				// attendo notifica count_ = 0
				try {
					mutex_.wait();
				} catch (InterruptedException e) {
					System.err.println("ExecutionHandler interrupted");
				}
			}
		}
		
		// Gestisce un Task e intercetta la notifyAll() 
		// invocata nel metodo run() di Task.
		private class TaskHandler implements Runnable {
			private final Task task_;
			// Mutex per la notifica di Task.run()
			private final Object mutex_ = new Object();
			private final int id_;
			
			public TaskHandler(int id, Task task) {
				task_ = task;
				id_ = id;
			}
			
			@Override
			public void run() {
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						task_.run(TaskHandler.this.mutex_);							
					}
				};
				
				synchronized (this.mutex_) {
					new Thread(runnable).start();
					try {
						this.mutex_.wait();
					} catch (InterruptedException e) {
						System.err.println("TaskHandler " + id_ + " interrupted");
					}
				}
			
				System.out.println("TaskHandler " + id_ + " notified");
				
				// si riattiva dopo la notify di task.run()
				synchronized (ExecutionHandler.this.mutex_) {
					--count_;	
					System.out.println("TaskHandler " + id_ + ": now count_ == " + count_);
				}
				
				// fuori da synchronized, sola lettura: potrebbero esserci più notifiche
				if (count_ != 0)
					return;
				
				// Il primo TaskHandler che rileva 'count_ == 0' notifica
				synchronized (ExecutionHandler.this.mutex_) {						
					System.out.println("TaskHandler " + id_ + " notified 'count_ == 0'");
					ExecutionHandler.this.mutex_.notifyAll();
				}
			}
		}
	}
}
