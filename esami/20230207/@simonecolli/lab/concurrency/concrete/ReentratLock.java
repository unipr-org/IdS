package exam_2023_02_07.lab.concurrency.concrete;

import exam_2023_02_07.lab.concurrency.abstracts.Condition;
import exam_2023_02_07.lab.concurrency.abstracts.Lock;

public class ReentratLock implements Lock{
	
	private Thread owner_;
	private int counter_;
	private Object mutex_;
	
	public ReentratLock() {
		owner_ = null;
		counter_ = 0;
		mutex_ = new Object();
	}
	
	@Override
	public void lock() throws InterruptedException {
		if(counter_ < 0)
			throw new IllegalMonitorStateException("counter_ < 0");
		Thread current = Thread.currentThread();
		synchronized (mutex_) {
			while(owner_ != null && owner_ != current)
				mutex_.wait();
			++counter_;
			if(owner_ == null)
				owner_ = current;
		}
	}

	@Override
	public void unlock() {
		if(counter_ < 0)
			throw new IllegalMonitorStateException("counter_ < 0");
		Thread current = Thread.currentThread();
		synchronized (mutex_) {
			if(owner_ != current)
				throw new IllegalMonitorStateException("owner_ != counter");
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
	private class InnerCondition implements Condition {
		
		private Object cond_;
		private InnerCondition() {
			this.cond_ = new Object();
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
					throw new IllegalMonitorStateException("owner_ != counter");
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
					throw new IllegalMonitorStateException("owner_ != counter");
				synchronized (cond_) {
					cond_.notifyAll();
				}
			}
		}
		
	}

}
