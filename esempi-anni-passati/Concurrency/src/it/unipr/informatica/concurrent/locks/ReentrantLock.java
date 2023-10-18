/*
 * ReentrantLock
 * 
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.concurrent.locks;

public class ReentrantLock implements Lock {
	private Thread owner;

	private int counter;

	private Object mutex;

	public ReentrantLock() {
		this.owner = null;

		this.mutex = new Object();

		this.counter = 0;
	}

	@Override
	public void lock() {
		Thread currentThread = Thread.currentThread();

		synchronized (mutex) {
			if (counter < 0)
				throw new IllegalMonitorStateException("counter < 0");

			while (owner != null && owner != currentThread)
				try {
					mutex.wait();
				} catch (InterruptedException interruptedException) {
					throw new IllegalMonitorStateException("interrupted");
				}

			if (owner == null)
				owner = currentThread;

			counter++;
		}
	}

	@Override
	public void unlock() {
		synchronized (mutex) {
			if (owner != Thread.currentThread())
				throw new IllegalMonitorStateException("owner != Thread.currentThread()");

			if (counter <= 0)
				throw new IllegalMonitorStateException("counter <= 0");

			counter--;

			if (counter == 0) {
				owner = null;

				mutex.notify();
			}
		}
	}

	@Override
	public Condition newCondition() {
		return new InnerCondition();
	}

	private class InnerCondition implements Condition {
		private Object condition;

		private InnerCondition() {
			this.condition = new Object();
		}

		@Override
		public void await() throws InterruptedException {
			unlock();

			synchronized (condition) {
				condition.wait();
			}

			lock();
		}

		@Override
		public void signal() {
			synchronized (mutex) {
				if (owner != Thread.currentThread())
					throw new IllegalMonitorStateException("owner != Thread.currentThread()");
			}

			synchronized (condition) {
				condition.notify();
			}
		}

		@Override
		public void signalAll() {
			synchronized (mutex) {
				if (owner != Thread.currentThread())
					throw new IllegalMonitorStateException("owner != Thread.currentThread()");
			}

			synchronized (condition) {
				condition.notifyAll();
			}
		}
	}
}
