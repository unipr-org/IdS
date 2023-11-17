package it.unipr.informatica.concurrent;

class SimpleThreadPoolExecutorService implements ExecutorService {
	private Thread[] workers;

	// Utilizzo una coda bloccante in cui salvare tutti i comandi da far eseguire ai thread
	private LinkedBlockingQueue<Runnable> tasks;

	private boolean shutdown;

	// count = numero di thread
	SimpleThreadPoolExecutorService(int count) {
		if (count < 1)
			throw new IllegalArgumentException("count < 1");

		this.shutdown = false;

		this.tasks = new LinkedBlockingQueue<>();

		this.workers = new Worker[count];

		for (int i = 0; i < count; ++i) {
			Worker worker = new Worker();

			workers[i] = worker;

			worker.start();
		}
	}

	@Override
	public Future<?> submit(Runnable task) {
		if (task == null)
			throw new NullPointerException("task == null");

		SimpleFuture<?> future = new SimpleFuture<>();

		execute(() -> {
			try {
				task.run();

				future.setValue(null);
			} catch (Throwable throwable) {
				future.setException(throwable);
			}
		});

		return future;
	}

	@Override
	public void submit(Runnable task, Callback<?> callback) {
		if (task == null)
			throw new NullPointerException("task == null");

		if (callback == null)
			throw new NullPointerException("callback == null");

		execute(() -> {
			try {
				task.run();

				callback.onSuccess(null);
			} catch (Throwable throwable) {
				callback.onFailure(throwable);
			}
		});
	}

	// Utilizzo i future
	@Override
	public <T> Future<T> submit(Callable<T> task) {
		if (task == null)
			throw new NullPointerException("task == null");

		SimpleFuture<T> future = new SimpleFuture<>();

		execute(() -> {
			try {
				T result = task.call();

				future.setValue(result);
			} catch (Throwable throwable) {
				future.setException(throwable);
			}
		});

		return future;
	}

	// Utilizzo i callback
	@Override
	public <T> void submit(Callable<T> task, Callback<T> callback) {
		if (task == null)
			throw new NullPointerException("task == null");

		if (callback == null)
			throw new NullPointerException("callback == null");

		execute(() -> {
			try {
				T result = task.call();

				callback.onSuccess(result);
			} catch (Throwable throwable) {
				callback.onFailure(throwable);
			}
		});
	}

	// Inserisce il comando nella coda bloccante
	@Override
	public void execute(Runnable command) {
		if (command == null)
			throw new NullPointerException("command == null");

		synchronized (tasks) {
			if (shutdown)
				throw new RejectedExecutionException("shutdown == true");

			tasks.put(command);
		}
	}

	// Spegne tutti i thread
	@Override
	public void shutdown() {
		synchronized (tasks) {
			shutdown = true;

			int length = workers.length;

			for (int i = 0; i < length; ++i)
				workers[i].interrupt();
		}
	}

	// Classe worker che estende Thread
	private class Worker extends Thread {
		
		// I thread girano all'infinito finche' non vengono spenti e le tasks sono finite
		@Override
		public void run() {
			// `for (;;)` equivale a scrivere `while(true)`
			for (;;) {
				synchronized (tasks) {
					if (shutdown && tasks.isEmpty())
						return;
				}

				try {
					Runnable runnable = tasks.take();

					runnable.run();
				} catch (InterruptedException InterruptedException) {
					return;
				} catch (Throwable throwable) {
					throwable.printStackTrace();
				}
			}
		}
	}
}
