package it.unipr.informatica.locks;

public class ReentrantLock implements Lock{
	private int counter_;
	private Object mutex_;
	private Thread owner_;
	
	public ReentrantLock() {
		counter_ = 0;
		mutex_ = new Object();
		owner_ = null;
	}
	
	@Override
	public void lock() {
		Thread currentThread = Thread.currentThread();
		synchronized (mutex_) {
			if(counter_ < 0)
				throw new IllegalArgumentException("counte_ < 0");
			while(owner_ != null && currentThread != owner_) {
				try {
					mutex_.wait();
				} catch (Exception e) {
					e.printStackTrace();
					throw new IllegalMonitorStateException(e.toString());
				}
			}
			++counter_;
			if(owner_ == null)
				owner_ = currentThread;
		}
	}
	
	@Override
	public void unlock() {
		Thread currentThread = Thread.currentThread();
		synchronized (mutex_) {
			if(counter_ < 0)
				throw new IllegalArgumentException("counte_ < 0");
			if(currentThread != owner_)
				throw new IllegalArgumentException("currentThread != owner_");
			--counter_;
			if(counter_ == 0) {
				owner_ = null;
				mutex_.notify();
			}
		}
	}
	@Override
	public Condition newCondition() {
		return new InnerCondition();
	}
	
	private class InnerCondition implements Condition{
		
		private Object condition_;
		
		public InnerCondition() {
			condition_ = new Object();
		}
		
		@Override
		public void await() throws InterruptedException {
			
			unlock();
			synchronized (condition_) {
				condition_.wait();
			}
			lock();
		}

		@Override
		public void signal() {
			Thread currentThread = Thread.currentThread();
			synchronized (mutex_) {
				if(owner_ != currentThread)
					throw new IllegalArgumentException("currentThread != owner_");
				synchronized (condition_) {
					condition_.notify();
				}
			}
		}

		@Override
		public void signalAll() {
			Thread currentThread = Thread.currentThread();
			synchronized (mutex_) {
				if(owner_ != currentThread)
					throw new IllegalArgumentException("currentThread != owner_");
				synchronized (condition_) {
					condition_.notifyAll();
				}
			}
			
		}
		
	}
}
