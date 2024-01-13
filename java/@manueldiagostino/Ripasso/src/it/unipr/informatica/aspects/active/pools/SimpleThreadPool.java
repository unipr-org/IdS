package it.unipr.informatica.aspects.active.pools;

import it.unipr.informatica.aspects.active.queue.ArrayBlockingQueue;
import it.unipr.informatica.aspects.active.queue.BlockingQueue;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class SimpleThreadPool implements ThreadPool {
	private BlockingQueue<Runnable> tasks;
	private Worker[] workers;
	private boolean shutdown;
		
	private static final int MIN_CONCURRENT_TASKS = 10;
	
	public SimpleThreadPool() {
		this(MIN_CONCURRENT_TASKS);
	}
	
	public SimpleThreadPool(int size) {
		if (size<1)
			throw new IllegalArgumentException("size<1");
		
		this.tasks = new ArrayBlockingQueue<Runnable>(50);
		this.workers = new Worker[size];
		this.shutdown = false;
		
		for (int i=0; i<size; ++i) {
			workers[i] = new Worker();
			workers[i].start();
		}
	}
	
	@Override
	public void execute(Runnable runnable) {
		Runnable inserter = () -> {			
			synchronized (tasks) {
				try {
					tasks.put(runnable);
				} catch (InterruptedException e) {
					System.err.println("Failed inserting task into threadPool");
				}
			}
		};
		
		new Thread(inserter).start();
	}

	@Override
	public void shutdown() {
		synchronized (tasks) {
			shutdown = true;
		}
		
		for (int i=0; i<workers.length; ++i) {
			workers[i].interrupt();
		}
	}
	
	private class Worker extends Thread {
		@Override
		public void run() {
			while (!shutdown) {
				
				try {
					Runnable task = tasks.take();
					task.run();
		
				} catch (InterruptedException e) {
					System.out.println("Worker interrupted");
					return;
				} catch (Throwable e) {
					System.err.println(e.getMessage());
					return;
				}
				
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		SimpleThreadPool pool = new SimpleThreadPool(10);
		
		for (int i=0; i<100; ++i) {
			int id = i;
			
			Runnable task = () -> {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
				System.out.println("Task " + id);
			};
			
			pool.execute(task);
			System.out.println("Task " + i + " inserted");
		}
		
		Thread.sleep(5000);
		pool.shutdown();
	}

	@Override
	public void submit(Runnable runnable, Callback<?> callback) {
		if (runnable == null)
			throw new IllegalArgumentException("runnable == null");
		if (callback == null)
			throw new IllegalArgumentException("callback == null");
		
		execute(() -> {
			
			try {
				runnable.run();
			} catch (Throwable e) {
				callback.onFailure(e);
			}
			
			callback.onSuccess(null);
		});
	}

	@Override
	public <T> Future<T> submit(Callable<T> callable) {
		if (callable == null)
			throw new IllegalArgumentException("runnable == null");
		SimpleFuture<T> future = new SimpleFuture<T>();
		
		execute(() -> {
			T result = null;
			try {
				result = callable.call();
			} catch (Throwable e) {
				future.setException(e);
			}
			future.setValue(result);
		});
		
		return future;
	}

	@Override
	public <T> void submit(Callable<T> callable, Callback<T> callback) {
		if (callable == null)
			throw new IllegalArgumentException("runnable == null");
		if (callback == null)
			throw new IllegalArgumentException("callback == null");
		
		execute(() -> {
			T result = null;
			try {
				result = callable.call();
			} catch (Throwable e) {
				callback.onFailure(e);
			}
			
			callback.onSuccess(result);
		});
	}

	@Override
	public Future<?> submit(Runnable runnable) {
		if (runnable == null)
			throw new IllegalArgumentException("runnable == null");
		SimpleFuture<?> future = new SimpleFuture<>();
		
		execute(() -> {
			try {
				runnable.run();
			} catch (Throwable e) {
				future.setException(e);
			}
			future.setValue(null);
		});
		
		return future;
	}

}
