/*
 * SimpleFuture<T>
 * 
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.concurrent;

class SimpleFuture<T> implements Future<T> {
	private Object mutex;

	private T value;

	private Throwable exception;

	private boolean done;

	SimpleFuture() {
		this.mutex = new Object();

		this.done = false;

		this.value = null;

		this.exception = null;
	}

	@Override
	public T get() throws InterruptedException, ExecutionException {
		synchronized (mutex) {
			while (!done)
				mutex.wait();

			if (exception != null)
				throw new ExecutionException(exception);

			return value;
		}
	}

	@Override
	public boolean isDone() {
		synchronized (mutex) {
			return done;
		}
	}

	void setValue(T object) {
		synchronized (mutex) {
			if (done)
				throw new IllegalStateException("done == true");

			value = object;

			done = true;

			mutex.notifyAll();
		}
	}

	void setException(Throwable throwable) {
		if (throwable == null)
			throw new IllegalArgumentException("throwable == null");

		synchronized (mutex) {
			if (done)
				throw new IllegalStateException("done == true");

			exception = throwable;

			done = true;

			mutex.notifyAll();
		}
	}
}
