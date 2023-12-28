package exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.concrete;

import exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.abstracts.Condition;
import exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.abstracts.Lock;

public class ReentrantLock implements Lock{

	private int counter_;
	private Thread owner_;
	private Object mutex_;
	
	public ReentrantLock() {
		this.counter_ = 0;
		this.owner_ = null;
		this.mutex_= new Object();
	}
	@Override
	public void lock() throws InterruptedException {
		if(counter_ < 0)
			throw new IllegalMonitorStateException("counter_ < 0");
		Thread current = Thread.currentThread();
		synchronized (mutex_) {
			while(owner_ != null && owner_ != current)
				mutex_.wait();
			
			if(owner_ == null)
				owner_ = current;
			++counter_;
		}
	}

	@Override
	public void unlock() {
		if(counter_ < 0)
			throw new IllegalMonitorStateException("counter_ < 0");
		Thread current = Thread.currentThread();
		synchronized (mutex_) {
			if(current != owner_)
				throw new IllegalMonitorStateException("current != owner_");
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
		private Object condition_;
		private InnerCondition() {
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
			Thread current = Thread.currentThread();
			synchronized (mutex_) {
				if(current != owner_)
					throw new IllegalMonitorStateException("current != owner_");
				synchronized (condition_) {
					condition_.notify();
				}
			}
		}

		@Override
		public void signalAll() {
			Thread current = Thread.currentThread();
			synchronized (mutex_) {
				if(current != owner_)
					throw new IllegalMonitorStateException("current != owner_");
				synchronized (condition_) {
					condition_.notifyAll();
				}
			}
		}
		
	}
}
