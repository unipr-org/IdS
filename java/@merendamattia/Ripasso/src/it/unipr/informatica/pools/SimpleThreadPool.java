package it.unipr.informatica.pools;

import it.unipr.informatica.blocking_queue.*;
import it.unipr.informatica.blocking_queue.model.BlockingQueue;
import it.unipr.informatica.executors.SimpleFuture;
import it.unipr.informatica.executors.model.Callable;
import it.unipr.informatica.executors.model.Callback;
import it.unipr.informatica.executors.model.Future;
import it.unipr.informatica.pools.model.ExecutorService;

public class SimpleThreadPool implements ExecutorService {

	public static final int MAX_LIMIT_TASKS = 20;
	public static final int MAX_WORKERS = 10;
	
	private Thread[] workers;
	private BlockingQueue<Runnable> tasks;
	private boolean shutdown;
	
	public SimpleThreadPool(int size) {
		if(size < 1)
			throw new IllegalArgumentException("size < 1");
		
		this.tasks = new ArrayBlockingQueue<Runnable>(MAX_LIMIT_TASKS);
		this.workers = new Worker[size];
		this.shutdown = false;
		
		for(int i = 0; i < size; ++i) {
			workers[i] = new Worker(i);
			workers[i].start();
		}
			
	}
	
	public SimpleThreadPool() {
		this(MAX_WORKERS);
	}
	
	@Override
	public void execute(Runnable runnable) {
		if(runnable == null)
			throw new IllegalArgumentException("runnable == null");
		
		if(shutdown)
			throw new IllegalMonitorStateException("shutdown == true");
		
		try {
			tasks.put(runnable);
		} catch (InterruptedException e) {
			System.err.println(e.getCause());
		}
		
	}

	@Override
	public void shutdown() {
		synchronized (tasks) {
			System.out.println("shutdown invoked");
			
			shutdown = true;
		}
		
	}
	
	@Override
	public void forceShutdown() {
		synchronized (tasks) {
			System.out.println("forceShutdown invoked");
			
			shutdown = true;
			
			for(int i = 0; i < workers.length; ++i) {
				workers[i].interrupt();
//				System.out.println("Thread.interrupted() - Thread" + i + ": " + workers[i].isInterrupted());
			}
				
		}
	}
	
	@Override
	public Future<?> submit(Runnable task) {
		if(task == null)
			throw new NullPointerException("task == null");
		
		SimpleFuture<?> future = new SimpleFuture<>();
		
		execute(() -> {
			try {
				task.run();
				future.setValue(null);
			} catch (Throwable e) {
				future.setException(e);
			}
		});
		
		return future;
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		if(task == null)
			throw new NullPointerException("task == null");
		
		SimpleFuture<T> future = new SimpleFuture<>();
		
		execute(() -> {
			try {
				T result = task.call();
				future.setValue(result);
			} catch (Throwable e) {
				future.setException(e);
			}
		});
		
		return future;
	}

	@Override
	public void submit(Runnable task, Callback<?> callback) {
		if(task == null)
			throw new NullPointerException("task == null");
		if(callback == null)
			throw new NullPointerException("callback == null");
		
		execute(() -> {
			try {
				task.run();
				callback.onSuccess(null);
			} catch (Throwable e) {
				callback.onFailure(e);
			}
		});
		
	}

	@Override
	public <T> void submit(Callable<T> task, Callback<T> callback) {
		if(task == null)
			throw new NullPointerException("task == null");
		if(callback == null)
			throw new NullPointerException("callback == null");
		
		execute(() -> {
			try {
				T result = task.call();
				callback.onSuccess(result);
				
			} catch (Throwable e) {
				callback.onFailure(e);
			}
		});
	}
	
	private class Worker extends Thread {
		private int id;
		
		public Worker(int id) {
			this.id = id;
			// System.out.println("Worker[" + id + "] - started");
		}
		
		@Override
		public void run() {
			while(true) {
				synchronized (tasks) {					
					if(shutdown && tasks.isEmpty()) {
						// System.out.println("Worker[" + id + "] - shutting down");
						return;
					}
				}
				
				try {
					Runnable runnable = tasks.take();
					// System.out.println("Worker[" + id + "] - takes new task");
					runnable.run();
					
				} catch (InterruptedException e) {
					System.err.println("Worker[" + id + "] - " + e.getCause());
					return; // Serve per far terminare il thread quando faccio shutdown
				} catch (Exception e) {
					System.out.println(e.getClass());
					throw e;
				}	
			}
		}
	} // ! class Worker
} // ! class SimpleThreadPool
