package exam_2023_01_12.lab.it.unipr.informatica.exam.pool;


import java.util.Iterator;

import exam_2023_01_12.lab.it.unipr.informatica.exam.Main;
import exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.abstracts.BlockingQueue;
import exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.concrete.UnlimitedBlockingQueue;

public class ThreadPool implements Executor{
	
	private Worker[] executors_;
	private volatile boolean shutdown_;
	
	private BlockingQueue<Runnable> tasks_;
	public static int N = 10;
	
	public ThreadPool() {

		executors_ = new Worker[N];
		shutdown_ = false;
		tasks_ = new UnlimitedBlockingQueue<>();
		
		for(int i = 0; i < N; ++i) {
			executors_[i] = new Worker();
			executors_[i].start();
		}
	}
	
	public void shutdown() {
		synchronized (tasks_) {
			shutdown_ = true;
			System.out.println("shutdown: " + shutdown_);
		}
	}
	
	@Override
	public void execute(Runnable task) {
		if(task == null)
			throw new IllegalArgumentException("task == null");
		if(shutdown_)
			throw new IllegalMonitorStateException("shutdown_");

		synchronized (tasks_) {
			try {
				tasks_.put(task);
				System.out.println("task added");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}					
		}
	}
	
	private class Worker extends Thread {
		@Override
		public void run() {
			
			while (true) {
				Runnable task;
				synchronized (tasks_) {
					System.out.println("tasks empty: " + tasks_.isEmpty());
					System.out.println("shutdown_: " + shutdown_);
					if(shutdown_ && tasks_.isEmpty())
						break;
				}
				try {
					task = tasks_.take();
				} catch (InterruptedException e) {
					throw new RuntimeException(e.getMessage());
				}
				task.run();
				
			}
			
			System.out.println("thread end");
		}
	}
	
	public static void main(String[] args) {
		ThreadPool pool = new ThreadPool();
		for(int i = 0; i < 10; ++i) {
			int tmp = i;
			pool.execute(()->{
				System.out.println("task " + tmp + " started");
				try {
					Thread.sleep((int)(Math.random() *1000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("task " + tmp + " ended");
			});
		}
		pool.shutdown();
	}

}
