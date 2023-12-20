package it.unipr.informatica.example;

import it.unipr.informatica.locks.*;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class ExecutorImpl implements Executor {
	
	@Override
	public void launch(Task[] tasks) {
		try {
			new ExecutionHandler(tasks);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Executor returned");
	}

	private class ExecutionHandler {
		private Lock lock = new ReentrantLock();
		private Condition areAllTerminated = lock.NewCondition();
		private int count;
		
		private TaskHandler[] taskHandlers;
		
		public ExecutionHandler(Task[] tasks) throws InterruptedException {
			count = tasks.length;
			taskHandlers = new TaskHandler[tasks.length];

			lock.lock();
			
			// lancia tutti i TaskHandlers
			for (int i=0; i<tasks.length; ++i) {
				taskHandlers[i] = new TaskHandler(tasks[i], i);	
				new Thread(taskHandlers[i]).start();
			}
			
			areAllTerminated.await();
			lock.unlock();
		}
		
		private class TaskHandler implements Runnable {
			private Object mutex = new Object();
			private int id;
			private Task task;
			
			public TaskHandler(Task task, int id) {
				this.id = id;
				this.task = task;
			}

			@Override
			public void run() {
				synchronized (mutex) {
					new Thread(new Runnable() {
						@Override
						public void run() {							
							task.run(mutex);
						}
					}).start();
					
					try {
						mutex.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					lock.lock();
					try {
						--count;
						
						if (count == 0) {
							areAllTerminated.signal();
							System.out.println("Task " + id + " notified parent" );
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						lock.unlock(); 
					}
				}
			}
		}
	}
}
