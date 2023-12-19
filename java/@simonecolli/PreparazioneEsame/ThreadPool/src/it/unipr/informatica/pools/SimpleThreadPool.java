package it.unipr.informatica.pools;

import it.unipr.informatica.blocking_queue.ArrayBlockingQueue;
import it.unipr.informatica.blocking_queue.BlockingQueue;
import it.unipr.informatica.pools.executors.Callable;
import it.unipr.informatica.pools.executors.Callback;
import it.unipr.informatica.pools.executors.Future;
import it.unipr.informatica.pools.executors.SimpleFuture;

public class SimpleThreadPool implements ExecutorService{
	
	private boolean shutdown_;
	private BlockingQueue<Runnable> taskBlockingQueue_;
	private Thread[] workers_;
	
	public final static int maxTaskNum_ = 20;
	public final static int maxWorkersNum_ = 10;
	
	public SimpleThreadPool() {
		this(maxWorkersNum_);
	}

	public SimpleThreadPool(int workerNum) {
		if(workerNum <= 0 || workerNum > maxWorkersNum_)
			throw new IllegalArgumentException("workerNum <= 0 || workerNum > maxWorkersNum_");
		
		shutdown_ = false;
		taskBlockingQueue_ = new ArrayBlockingQueue<Runnable>(maxTaskNum_);
		workers_ = new Worker[workerNum];
		
		for (int i = 0; i < workerNum; ++i) {
			workers_[i] = new Worker();

			workers_[i].start();
			System.err.println("worker start " + i);
		}
	}
	
	@Override
	public void execute(Runnable command) {
		System.err.println("new command");
		
		if(shutdown_)
			throw new IllegalMonitorStateException("shutdown = true");
		if(command == null)
			throw new IllegalArgumentException("command == null");
		
		synchronized (taskBlockingQueue_) {
			try {
				taskBlockingQueue_.put(command);

//				System.err.println("puttet");
			} catch (InterruptedException e) {
				System.err.println(e.getCause());
			}
		}
		
	}

	@Override
	public void shutdown() {
		synchronized (taskBlockingQueue_) {
			shutdown_ = true;			
		}
	}
	
	
	private class Worker extends Thread{
		@Override
		public void run() {
			
			while (true) {
				synchronized (taskBlockingQueue_) {
					if(shutdown_ && taskBlockingQueue_.isEmpty())
						return;
				}
				Runnable task;
				try {
//					System.err.println("taking.....");
					task = taskBlockingQueue_.take();
//					System.err.println("task got");
					task.run();
				} catch (InterruptedException e) {
					System.err.println(e.getCause());
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
				
			}
		}
	}


	@Override
	public Future<?> submit(Runnable task) {
		if(task == null)
			throw new IllegalArgumentException("runnable == null");
		SimpleFuture<?> future = new SimpleFuture<>();
		
//		eseguo nel pool
		execute(() ->{
			try {
				task.run();
				future.setValue(null);
			}catch (Exception e) {
				future.setThrowable(e);
			}
		});
		
		return future;
	}

	@Override
	public <RetType> Future<RetType> submit(Callable<RetType> task) {
		if(task == null)
			throw new IllegalArgumentException("runnable == null");
		SimpleFuture<RetType> future = new SimpleFuture<>();
		
//		eseguo nel pool
		execute(() ->{
			try {
				future.setValue(task.call());
			}catch (Exception e) {
				future.setThrowable(e);
			}
		});
		
		return future;
	}

	@Override
	public void submit(Runnable task, Callback<?> callback) {
		if(task == null)
			throw new IllegalArgumentException("runnable == null");
		if(callback == null)
			throw new IllegalArgumentException("callback == null");
		execute(()->{
			try {
				task.run();
				callback.onSuccess(null);
			} catch (Exception e) {
				callback.onFailure(e);
			}
		});
	}

	@Override
	public <RetType> void submit(Callable<RetType> task, Callback<RetType> callback) {
		if(task == null)
			throw new IllegalArgumentException("runnable == null");
		if(callback == null)
			throw new IllegalArgumentException("callback == null");
		execute(()->{
			try {
				callback.onSuccess(task.call());
			} catch (Exception e) {
				callback.onFailure(e);
			}
		});
	}

}
