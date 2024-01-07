package it.unipr.informatica.concurrent.pool;

import java.util.concurrent.RejectedExecutionException;

import it.unipr.informatica.concurrent.locks.LinkedBlockingQueue;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 * 
 * Implementazione di un pool di thread che sfrutta le LinkedBlockingQueue
 */
public class SimpleThreadPoolExecutorService implements ExecutorService {
	
	private Worker[] workers_;
	private LinkedBlockingQueue<Runnable> tasks_;
	
	private Flag shutdown_;
	
	public SimpleThreadPoolExecutorService() {
		this(4);
	}
	
	public SimpleThreadPoolExecutorService(int count) {
		if (count < 1)
			throw new IllegalArgumentException("count < 1");
		
		workers_ = new Worker[count];
		tasks_ = new LinkedBlockingQueue<Runnable>();
		shutdown_ = new Flag();
		shutdown_.content = false;
		
		Worker worker;
		for (int i=0; i<workers_.length; ++i) {
			worker = new Worker();
			workers_[i] = worker;
			worker.start();
		}
	}
	 
	@Override
	public void execute(Runnable command) {
		if (command == null)
			throw new IllegalArgumentException("command == null");
		
		synchronized (shutdown_) {
			if (shutdown_.content == true)
				throw new RejectedExecutionException("shutdown == true");
			
			tasks_.put(command); 
			// È dentro `synchronized (shutdown_)` perché inserisco tasks solo 
			// sono sicuro che shutdown_.content è false
		}
	}
	
	@Override
	public <T> void submit(Callable<T> callable, Callback<T> callback) {
		if (callable == null)
			throw new IllegalArgumentException("callable == null");
		if (callback == null)
			throw new IllegalArgumentException("callback == null");
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {
					callable.call();
					callback.onSuccess(null);
				} catch (Exception e) {
					callback.onFailure(e);
				}
			}
		};
		
		this.execute(runnable);
	}
	
	@Override
	public void shutdown() {
		synchronized (shutdown_) {
			shutdown_.content = true;
		}
		
		for (int i=0; i<workers_.length; ++i)
			workers_[i].interrupt();
	}
	
	private class Worker extends Thread {
		@Override
		public void run() {
			Runnable runnable;
			
			while (true) {
				synchronized (shutdown_) {
					if (shutdown_.content == true && tasks_.isEmpty())
						return;
				}
				
				try {
					runnable = tasks_.take();
					runnable.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				} catch (Throwable throwable) {
					throwable.printStackTrace();
				}
			}
		}
	}
	
	private class Flag {
		public boolean content;
	}
}
