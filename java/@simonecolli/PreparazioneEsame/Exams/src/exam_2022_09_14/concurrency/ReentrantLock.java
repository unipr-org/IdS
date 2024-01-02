package exam_2022_09_14.concurrency;

public class ReentrantLock implements Lock{
	
	private Object mutex_;
	private int counter;
	private Thread owner_;
	
	public ReentrantLock() {
		mutex_ = new Object();
		counter = 0;
		owner_ = null;
	}
	
	@Override
	public void lock() throws InterruptedException {
		if(counter < 0)
			throw new IllegalMonitorStateException("counter < 0counter < 0");
		Thread current =  Thread.currentThread();
		synchronized (mutex_) {
			while(owner_ != null && owner_ != current)
				mutex_.wait();
			if(owner_== null)
				owner_ = current;
			++counter;
		}
	}

	@Override
	public void unlock() {
		if(counter < 0)
			throw new IllegalMonitorStateException("counter < 0");
		Thread current =  Thread.currentThread();
		
		synchronized (mutex_) {
			
			if(owner_ != current)
				throw new IllegalMonitorStateException("owner_ != current");
			
			--counter;
			if(counter == 0) {
				owner_ = null;
				mutex_.notify();
			}
		}
	}

	@Override
	public Condition newCondition() {
		return new InnerCond();
	}
	
	private class InnerCond implements Condition {

		private Object cond_; 
		private InnerCond() {
			cond_ = new Object();
		}
		@Override
		public void await() throws InterruptedException {
			unlock();
			synchronized (cond_) {
				cond_.wait();
			}
			lock();
		}

		@Override
		public void signal() {
			Thread current = Thread.currentThread();
			synchronized (mutex_) {
				if(owner_ != current)
					throw new IllegalMonitorStateException("owner_ != current");
				synchronized (cond_) {
					cond_.notify();
				}
			}
		}

		@Override
		public void signalAll() {
			Thread current = Thread.currentThread();
			synchronized (mutex_) {
				if(owner_ != current)
					throw new IllegalMonitorStateException("owner_ != current");
				synchronized (cond_) {
					cond_.notifyAll();
				}
			}
		}
		
	}
}
