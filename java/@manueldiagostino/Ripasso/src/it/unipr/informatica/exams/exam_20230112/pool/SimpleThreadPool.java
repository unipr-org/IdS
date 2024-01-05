package it.unipr.informatica.exams.exam_20230112.pool;

import java.util.ArrayList;

import it.unipr.informatica.exams.exam_20230112.concurrency.ArrayBlockingQueue;
import it.unipr.informatica.exams.exam_20230112.concurrency.BlockingQueue;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class SimpleThreadPool implements ThreadPool {
	private BlockingQueue<Runnable> tasks;
	private Worker[] workers;
	private boolean shutdown;

	public static final int POOL_SIZE = 10;
	public static final int MAX_NUM_TASKS = 50;

	public SimpleThreadPool() {
		this.shutdown = false;
		this.tasks = new ArrayBlockingQueue<Runnable>(MAX_NUM_TASKS);

		this.workers = new Worker[POOL_SIZE];
		for (int i = 0; i < workers.length; ++i) {
			workers[i] = new Worker();
			workers[i].start();
		}
	}

	@Override
	public void execute(Runnable task) {
		synchronized (tasks) {
			try {
				// System.out.println("Inserting task");
				tasks.put(task);
			} catch (InterruptedException e) {
				System.out.println("ThreadPool interrupted");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void shutdown() {
		synchronized (tasks) {
			shutdown = true;
		}

		for (int i = 0; i < workers.length; ++i)
			workers[i].interrupt();
	}

	private class Worker extends Thread {

		@Override
		public void run() {
			while (true) {
				synchronized (tasks) {
					if (shutdown && tasks.isEmpty())
						return;
				}

				Runnable runnable;
				try {
					runnable = tasks.take();
					runnable.run();
				} catch (InterruptedException e) {
					// System.out.println("Worker interrupted");
					return;
				}
			}
		}
	} // ! END WORKER

	public static void main(String[] args) {
		SimpleThreadPool pool = new SimpleThreadPool();

		for (int i = 0; i < 6; ++i) {
			int id = i;
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					System.out.println("Task " + id + " entered");
					try {
						Thread.sleep(2500 + (int) (1000 * Math.random()));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("Task " + id + " exited");
				}
			};

			pool.execute(runnable);
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.shutdown();
	}
}
