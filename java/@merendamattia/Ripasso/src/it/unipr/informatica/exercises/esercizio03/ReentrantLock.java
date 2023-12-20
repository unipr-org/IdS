package it.unipr.informatica.exercises.esercizio03;

public class ReentrantLock implements Lock {

	private Object mutex;
	private int counter;
	private Thread owner;
	
	public ReentrantLock() {
		this.mutex = new Object();
		this.owner = Thread.currentThread();
		this.counter = 0;
	}
	
	@Override
	public void lock() {
		synchronized (mutex) {
			Thread currentThread = Thread.currentThread();
			
			if(counter < 0)
				throw new IllegalArgumentException("counter < 0");
			
			while(owner != null && owner != currentThread) {
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
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
			
			if(counter == 1)
				owner = null;
			
			--counter;
		}
	}

	@Override
	public Condition newCondition() {
		return new InnerCondition();
	}
	
	private class InnerCondition implements Condition {
		private Object cond;
		
		public InnerCondition() {
			this.cond = new Object();
		}
		
		@Override
		public void await() throws InterruptedException {
			unlock();
			
			try {
				synchronized (cond) {
					cond.wait();
				}
			} finally {
				lock();
			}
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
		
	} // ! InnerCondition

} // ! ReentrantLock
