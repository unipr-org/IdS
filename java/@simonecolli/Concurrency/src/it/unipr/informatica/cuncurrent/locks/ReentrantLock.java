package it.unipr.informatica.cuncurrent.locks;

public class ReentrantLock implements Lock{
	
	private Thread owner_;
	private int counter_;
	private Object mutex_;
	
	public ReentrantLock() {
		this.owner_ = null;
		this.counter_ = 0;
		this.mutex_ = new Object();
	}
	
	@Override
	public void lock() {

		Thread currentThread = Thread.currentThread();
		
		synchronized (mutex_) {
			if (counter_ < 0)
				throw new IllegalMonitorStateException("conuter < 0");
			while ( owner_ != null && owner_ != currentThread) {
				try {
					mutex_.wait();
				} catch (InterruptedException e) {
					throw new IllegalMonitorStateException("Interrupted");
				}
				if(owner_ == null)
					owner_ = currentThread;
				++counter_;
			}	
		}
	}
	@Override
	public void unlock() {
		synchronized (mutex_) {
			if (owner_ != Thread.currentThread())
				throw new IllegalMonitorStateException("owner != Thread.currentThread()");
			if (counter_ <= 0)
				throw new IllegalMonitorStateException("counter <= 0");
			--counter_;
			if (counter_ == 0) {
				owner_ = null;
				mutex_.notify();
			}
		}
	}
	@Override
	public Condition newCondition() {
		return new InnerCondition();
	}
	
	private class InnerCondition implements Condition {
	
		private Object condition_;
		
		private InnerCondition() {
			this.condition_= new Object();
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
			synchronized (mutex_) {
				if (owner_ != Thread.currentThread())
					throw new IllegalMonitorStateException("owner != Thread.currentThread()");
			}
			synchronized (condition_) {
				condition_.notify();
			}
		}
		@Override
		public void signalAll() {
			synchronized (mutex_) {
				if(owner_ != Thread.currentThread())
					throw new IllegalMonitorStateException("owner != Thread.currentThread()");
			}
			synchronized (condition_) {
				condition_.notifyAll();
			}
		}
	}
	
}
