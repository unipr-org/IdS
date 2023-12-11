package it.unipr.informatica.pools;

import it.unipr.informatica.blockingQueue.ArrayBlockingQueue;
import it.unipr.informatica.executors.Callable;
import it.unipr.informatica.executors.Callback;
import it.unipr.informatica.executors.Future;

public class PriorityQueueThreadPool implements ExecutorService {
	private Worker[] workers_;
	private Dispatcher dispatcher_;
	private boolean shutdown_;
	private ArrayBlockingQueue<Runnable> executionQueue_;
	
	private ArrayBlockingQueue<Runnable> lowPriorQueue_;
	private ArrayBlockingQueue<Runnable> mediumPriorQueue_;
	private ArrayBlockingQueue<Runnable> highPriorQueue_;

	public static enum Priority {
		LOW,
		MEDIUM,
		HIGH
	}
	
	public static final int MAX_NUMBER_OF_WORKERS = 20;
	
	
	public PriorityQueueThreadPool(int size) {
		if (size < 1 || size > MAX_NUMBER_OF_WORKERS)
			throw new IllegalArgumentException("Invalid size");
		
		workers_ = new Worker[size];
		dispatcher_ = new Dispatcher();
		shutdown_ = false;
		
		executionQueue_ = new ArrayBlockingQueue<Runnable>(100);
		lowPriorQueue_ = new ArrayBlockingQueue<Runnable>(50);
		mediumPriorQueue_ = new ArrayBlockingQueue<Runnable>(50);
		highPriorQueue_ = new ArrayBlockingQueue<Runnable>(50);
		
		dispatcher_.start();
		for (int i=0; i<size; ++i) {
			workers_[i] = new Worker();
			workers_[i].start();
		}
	}
	
	@Override
	public void execute(Runnable runnable) {
		executeWithPriority(runnable, Priority.MEDIUM);
	}
	
	public void executeWithPriority(Runnable runnable, Priority priority) {
		synchronized (executionQueue_) {
			try {
				if (priority == Priority.HIGH) {
					highPriorQueue_.put(runnable);
				}
				else if (priority == Priority.MEDIUM) {
					mediumPriorQueue_.put(runnable);
				}
				else {
					lowPriorQueue_.put(runnable);
				}
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			
//			System.out.println("Inserted");
			executionQueue_.notifyAll();
		}
	}

	@Override
	public void shutdown() {
		synchronized (executionQueue_) {
			shutdown_ = true;
			
			dispatcher_.interrupt();
			
			for (int i=0; i<workers_.length; ++i)
				workers_[i].interrupt();
		}
	}

	@Override
	public Future<Void> submit(Runnable runnable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void submit(Runnable runnable, Callback<?> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> Future<T> submit(Callable<T> callable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void submit(Callable<T> callable, Callback<T> callback) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	private class Worker extends Thread {
		@Override
		public void run() {
			Runnable runnable;
			while (true) {
				synchronized (executionQueue_) {
					if (shutdown_ && executionQueue_.isEmpty())
						return;					
				}
				
				try {
					runnable = executionQueue_.take();
					runnable.run();	
				} catch (InterruptedException InterruptedException) {
					System.out.println("Worker interrupted");
					return;
				} catch (Exception e) {
					System.out.println(e.getClass());
					return;
				}
			}
		}
	}
	
	private class Dispatcher extends Thread {
		@Override
		public void run() {
			int lowQueueLenght;	
			int mediumQueueLenght;	
			int highQueueLenght;
			
			int totalNumberOfTasks;
			
			while (true) {
				synchronized (executionQueue_) {
					if (shutdown_ && executionQueue_.isEmpty())
						return;					
					
					if (highPriorQueue_.getSize() == 0 && mediumPriorQueue_.getSize() == 0 && lowPriorQueue_.getSize() == 0)
						try {
							System.out.println("Empty queues");
							executionQueue_.wait();
						} catch (InterruptedException e) {
							System.out.println("Dispatcher interrupted");
							return;
						}
					
					try {
						lowQueueLenght = lowPriorQueue_.getSize();
						mediumQueueLenght = mediumPriorQueue_.getSize();	
						highQueueLenght = highPriorQueue_.getSize();
						
						System.out.println("lowLenght: " + lowQueueLenght);
						System.out.println("mediumLenght: " + mediumQueueLenght);
						System.out.println("highLenght: " + highQueueLenght);
						
						totalNumberOfTasks = (lowQueueLenght+mediumQueueLenght+highQueueLenght);
						int lowTasks;
						int mediumTasks;
						int highTasks;
						
						if (totalNumberOfTasks > workers_.length) {
							totalNumberOfTasks = workers_.length;
							
							lowTasks = (int)(10.0/100 * totalNumberOfTasks);
							mediumTasks = (int)(30.0/100 * totalNumberOfTasks);
							highTasks = (int)(60.0/100 * totalNumberOfTasks);
						} else {
							lowTasks = lowQueueLenght;
							mediumTasks = mediumQueueLenght;
							highTasks = highQueueLenght;
						}
						
						System.out.println("Total number of processed tasks: " + totalNumberOfTasks);
						System.out.println("\tlow: " + lowTasks);
						System.out.println("\tmedium: " + mediumTasks);
						System.out.println("\thigh: " + highTasks);
						System.out.println();
						
						Runnable task = null;
						for (int i=highTasks; totalNumberOfTasks>0 && i>0; --i) {
							task = highPriorQueue_.take();
							executionQueue_.put(task);
							--totalNumberOfTasks;
						}	
						
						for (int i=mediumTasks; totalNumberOfTasks>0 && i>0; --i) {
							task = mediumPriorQueue_.take();
							executionQueue_.put(task);
							--totalNumberOfTasks;
						}
						
						for (int i=lowTasks; totalNumberOfTasks>0 && i>0; --i) {
							task = lowPriorQueue_.take();
							executionQueue_.put(task);
							--totalNumberOfTasks;
						}
					} catch (InterruptedException InterruptedException) {
						System.out.println("Dispatcher interrupted");
						return;
					} catch (Exception e) {
						System.out.println(e.getClass());
						return;
					}
				}
			}
		}
	}

}
