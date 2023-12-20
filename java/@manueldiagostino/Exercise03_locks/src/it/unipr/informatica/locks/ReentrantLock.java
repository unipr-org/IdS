package it.unipr.informatica.locks;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class ReentrantLock implements Lock {
	private int count;
	private Thread owner;
	private Object mutex;

	public ReentrantLock() {
		count = 0;
		owner = null;
		mutex = new Object();
	}
	
	@Override
	public void lock() {
		if (count < 0)
			throw new IllegalMonitorStateException("count < 0");
		
		Thread currentThread = Thread.currentThread();
		
		synchronized (mutex) {
			while (owner != null && owner != currentThread)
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if (owner == null)
				owner = currentThread;
			
			++count;
		}
	}
	
	@Override
	public void unlock() {
		if (count < 1)
			throw new IllegalMonitorStateException("count < 1");
		if (owner != Thread.currentThread())
			throw new IllegalMonitorStateException("owner != Thread.currentThread()");
		
		synchronized (mutex) {
			--count;
			
			if (count == 0) {
				owner = null;
				mutex.notify();
			}
		}	
	}
	
	@Override
	public Condition NewCondition() {
		return new InnerCondition();
	}
	
	private class InnerCondition implements Condition {
		private Object condition;
		
		private InnerCondition() {
			condition = new Object();
		}
		
		@Override
		public void await() throws InterruptedException {
			unlock();
			
			try {
				synchronized (condition) {
					condition.wait();
				}
			} finally {
				lock();
			}
		}

		@Override
		public void signal() throws InterruptedException {
			synchronized (mutex) {
				if (owner != Thread.currentThread())
					throw new IllegalMonitorStateException("signal(): owner_ != currentThread");
			}
			synchronized (condition) {
				condition.notify();
			}			
		}

		@Override
		public void signalAll() throws InterruptedException {
			if (owner != Thread.currentThread())
				throw new IllegalMonitorStateException("owner != Thread.currentThread()");
			
			synchronized (condition) {
				condition.notifyAll();
			}
		}
	}
}
