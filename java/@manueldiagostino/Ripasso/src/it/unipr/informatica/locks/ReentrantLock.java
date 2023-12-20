package it.unipr.informatica.locks;

public class ReentrantLock implements Lock {
	private Thread owner_;
	private int counter_;
	private final Object mutex_;
	
	public ReentrantLock() {
		mutex_ = new Object();
		counter_ = 0;
		owner_ = null;
	}
	
	@Override
	public void lock() {
		
		synchronized (mutex_) {
			Thread currentThread = Thread.currentThread();
			if (counter_ < 0)
				throw new IllegalArgumentException("counter_ < 0");
			
			while (owner_ != null && owner_ != currentThread) {
				try {
					mutex_.wait();
				} catch (InterruptedException e) {
					throw new IllegalMonitorStateException(e.toString());
				}
			}
			
			if (owner_ == null)
				owner_ = currentThread;
			
			++counter_;
		}
	}

	@Override
	public void unlock() {		
		synchronized (mutex_) {			
			Thread currentThread = Thread.currentThread();
			if (owner_ != currentThread)
				throw new IllegalMonitorStateException("unlock(): owner_ != currentThread");
			if (counter_ <= 0)
				throw new IllegalMonitorStateException("unlock(): counter <= 0");
			
			--counter_;
			
			if (counter_ == 0) {
				owner_ = null;
				mutex_.notify();
			}
		}
	}

	public void printStat() {
		System.out.println("<" + Thread.currentThread().getName() + ">");
		System.out.println("\towner: " + owner_);
		System.out.println("\tcounter: " + counter_);
	}

	@Override
	public Condition NewCondition() {
		return new ConditionImpl();
	}
	
	private class ConditionImpl implements Condition {
		private Object condition_;
		
		public ConditionImpl() {
			condition_ = new Object();
		}
		
		@Override
		public void await() throws InterruptedException {
<<<<<<< HEAD
			unlock();
			try {
				synchronized (condition_) {
					condition_.wait();
				}
=======
			try {
				unlock();
			
				synchronized (condition_) {
					condition_.wait();
				}
			
>>>>>>> upstream/main
			} finally {
				lock();
			}
		}

		@Override
		public void signal() {
			synchronized (mutex_) {
				if (owner_ != Thread.currentThread())
					throw new IllegalMonitorStateException("signal(): owner_ != currentThread");
			}
			
			synchronized (condition_) {
				condition_.notify();
			}
		}

		@Override
		public void signalAll() {
			synchronized (mutex_) {
				if (owner_ != Thread.currentThread())
					throw new IllegalMonitorStateException("signalAll(): owner_ != currentThread");
			}
			
			synchronized (condition_) {
				condition_.notifyAll();
			}
		}
	}

}
