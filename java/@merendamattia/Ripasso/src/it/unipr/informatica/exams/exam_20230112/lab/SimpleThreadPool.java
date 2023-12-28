package it.unipr.informatica.exams.exam_20230112.lab;

public class SimpleThreadPool implements ExecutorService {
	private Worker[] workers;
	private final int NUM_WORKERS; 
	private boolean shutdown;
	private Object mutex;
	private BlockingQueue<Runnable> tasks;
	
	public SimpleThreadPool(int dim) {
		if(dim < 1)
			throw new IllegalArgumentException("dim < 1");
		
		NUM_WORKERS = dim;
		this.workers = new Worker[dim];
		this.shutdown = false;
		this.mutex = new Object();
		this.tasks = new ArrayBlockingQueue<Runnable>();
		
		for(int i = 0; i < NUM_WORKERS; i++)
			workers[i] = new Worker(i);
		
		for(int i = 0; i < NUM_WORKERS; i++)
			workers[i].start();
	}
	
	@Override
	public void execute(Runnable task) {
		if(task == null)
			throw new IllegalArgumentException("task == null");
		
		try {
			tasks.put(task);
		} catch (InterruptedException e) {
			System.err.println(e.getCause());
		}
	}

	@Override
	public void shutdown() {
		synchronized (mutex) {
			if(shutdown)
				throw new IllegalMonitorStateException("isDone");
			for(int i = 0; i < NUM_WORKERS; i++)
				workers[i].interrupt();
			shutdown = true;
		}
		
	}
	
	private class Worker extends Thread{
		private int ID;
		
		public Worker(int ID) {
			this.ID = ID;
		}
		
		@Override
		public void run() {
			while(true) {
				synchronized (tasks) {
					if(shutdown && tasks.isEmpty())
						return;
				}
			
				try {
					Runnable task = tasks.take();
					task.run();
				} catch (InterruptedException e) {
					if(!shutdown)
						System.err.println("Thread" + ID + " " + e.getCause());
				}
			}
		}
	} // ! Worker

} // ! SimpleThreadPool
