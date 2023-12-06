package it.unipr.informatica.locks;

public class ReentrantLock implements Lock {
	private Thread owner;
	private Object mutex;
	private int counter;
	
	public ReentrantLock() {
		this.owner = null;
		this.mutex = new Object();
		this.counter = 0;
	}
	
	@Override
	public void lock() {
		synchronized (mutex) {
			Thread currentThread = Thread.currentThread();
			
			if(counter < 0)
				throw new IllegalArgumentException("counter < 0");
			
			while (owner != null && currentThread != owner) {
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					new IllegalArgumentException("mutex.wait();");
				}
			}
			
			++counter;
			
			owner = currentThread;
		}	
	}

	@Override
	public void unlock() {
		synchronized (mutex) {
			Thread currentThread = Thread.currentThread();
			
			if(counter < 0)
				throw new IllegalArgumentException("counter < 0");
			
			if(owner != currentThread)
				throw new IllegalArgumentException("owner != currentThread");
			
			--counter;
			
			if(counter == 0) {
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
		private Object cond;
		
		private InnerCondition() {
			cond = new Object();
		}
		
		@Override
		public void await() throws InterruptedException {
			unlock();
			
			synchronized (cond) {
				cond.wait();
			}
			
			lock();			
		}

		@Override
		public void signal() {
			synchronized (mutex) {
				if(owner != Thread.currentThread())
					throw new IllegalArgumentException("owner != Thread.currentThread()");
			}
			
			synchronized (cond) {
				cond.notify();
			}
		}

		@Override
		public void signalAll() {
			synchronized (mutex) {
				if(owner != Thread.currentThread())
					throw new IllegalArgumentException("owner != Thread.currentThread()");
			}
			
			synchronized (cond) {
				cond.notifyAll();
			}
		}
	}
}
