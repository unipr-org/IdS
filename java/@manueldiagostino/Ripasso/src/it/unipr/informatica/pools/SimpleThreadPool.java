package it.unipr.informatica.pools;

import it.unipr.informatica.blockingQueue.ArrayBlockingQueue;
import it.unipr.informatica.blockingQueue.BlockingQueue;
import it.unipr.informatica.executors.Callable;
import it.unipr.informatica.executors.Callback;
import it.unipr.informatica.executors.Future;
import it.unipr.informatica.executors.SimpleFuture;

public class SimpleThreadPool implements ExecutorService {
	private Thread[] workers_;
	private BlockingQueue<Runnable> tasks_;
	private boolean shutdown_;
	
	public final static int MAX_NUMBER_OF_TASKS = 20;
	public final static int MAX_NUMBER_OF_WORKERS = 10;
	
	public SimpleThreadPool() {
		this(MAX_NUMBER_OF_WORKERS);
	}
	
	public SimpleThreadPool(int size) {
		if (size <= 0 || size > MAX_NUMBER_OF_WORKERS)
			throw new IllegalArgumentException("Illegal size: max value is " + MAX_NUMBER_OF_WORKERS);
		
		workers_ = new Worker[size];
		tasks_ = new ArrayBlockingQueue<Runnable>(MAX_NUMBER_OF_TASKS);
		shutdown_ = false;
		
		Worker worker;
		for (int i=0; i<workers_.length; ++i) {
			worker = new Worker();
			workers_[i] = worker;
			worker.start();
		}
	}
	
	@Override
	public void execute(Runnable runnable) {
		if (runnable == null)
			throw new IllegalArgumentException("runnable == null");
		if (shutdown_)
			throw new IllegalMonitorStateException("shutdown_ already true");
		
		
		try {
			tasks_.put(runnable);
		} catch (InterruptedException e) {
			System.err.println(e.getCause());
		}
	}

	@Override
	public void shutdown() {
		synchronized (tasks_) {
			shutdown_ = true;
			
			for (int i=0; i<workers_.length; ++i)
				workers_[i].interrupt();
		}
	}

	@Override
	public Future<Void> submit(Runnable runnable) {
		if (runnable == null)
			throw new IllegalArgumentException("runnable == null");
		
		SimpleFuture<Void> future = new SimpleFuture<>();
		
		Runnable task = () -> {
			try {
				runnable.run();
				future.setValue(null); // runnable riuscita
			} catch (Throwable ie) {
				future.setException(ie); // runnable lancia eccezione
			}
		};
		this.execute(task);
		
		return future;
	}

	@Override
	public void submit(Runnable runnable, Callback<?> callback) {
		if (runnable == null)
			throw new IllegalArgumentException("runnable == null");
		if (callback == null)
			throw new IllegalArgumentException("callback == null");
		
		Runnable task = () -> {
			try {
				runnable.run();
				callback.onSuccess(null); // runnable riuscita
			} catch (Throwable ie) {
				callback.onFailure(ie); // runnable lancia eccezione
			}
		};
		this.execute(task);
	}

	@Override
	public <T> Future<T> submit(Callable<T> callable) {
		if (callable == null)
			throw new IllegalArgumentException("callable == null");
		
		SimpleFuture<T> future = new SimpleFuture<T>();
		Runnable task = () -> {
			try {
				future.setValue(callable.call()); // runnable riuscita
			} catch (Throwable ie) {
				future.setException(ie); // runnable lancia eccezione
			}
		};
		
		this.execute(task);
		return future;
	}

	@Override
	public <T> void submit(Callable<T> callable, Callback<T> callback) {
		if (callable == null)
			throw new IllegalArgumentException("callable == null");
		if (callback == null)
			throw new IllegalArgumentException("callback == null");
		
		Runnable task = () -> {
			try {
				callback.onSuccess(callable.call()); // runnable riuscita
			} catch (Throwable ie) {
				callback.onFailure(ie); // runnable lancia eccezione
			}
		};
		
		this.execute(task);	
	}
	
	private class Worker extends Thread {
		@Override
		public void run() {	
			Runnable runnable;
			while (true) {
				synchronized (tasks_) {
					if (shutdown_ && tasks_.isEmpty())
						return;					
				}
				
				try {
					runnable = tasks_.take();
					runnable.run();			
				} catch (InterruptedException InterruptedException) {
					System.out.println("Worker interrupted");
					return;
//				} catch (IllegalMonitorStateException IllegalMonitorStateException) {
//					return;
				} catch (Throwable e) {
					//e.printStackTrace();
					System.out.println(e.getClass());
				}
			}
		}
	}

}
