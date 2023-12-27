package it.unipr.informatica.exams.exam_20230112;

public class ReentrantLock implements Lock {
	private Object mutex;
	private Thread owner;
	private int counter;
	
	public ReentrantLock() {
		this.mutex = new Object();
		this.owner = null;
		this.counter = 0;
	}
	
	@Override
	public void lock() {
		synchronized (mutex) {
			Thread current = Thread.currentThread();
			
			if(counter < 0)
				throw new IllegalArgumentException("counter < 0");
			
			while(current != owner && owner != null) {
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					System.err.println(e.getCause());
				}
			}
			
			++counter;
			owner = current;
		}
	}

	@Override
	public void unlock() {
		synchronized (mutex) {
			Thread current = Thread.currentThread();
			
			if(counter < 0)
				throw new IllegalArgumentException("counter < 0");
			
			if(owner == null || owner != current)
				throw new IllegalArgumentException("owner == null || owner != current");
			
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
		private Object condition;
		
		public InnerCondition() {
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
				if(owner != Thread.currentThread())
					throw new IllegalArgumentException("owner != Thread.currentThread()");
			}
			
			synchronized (condition) {
				condition.notify();
			}
		}

		@Override
		public void signalAll() {
			synchronized (mutex) {
				if(owner != Thread.currentThread())
					throw new IllegalArgumentException("owner != Thread.currentThread()");
			}
			synchronized (condition) {
				condition.notifyAll();
			}
		}
		
	}

} // ! ReentrantLock
