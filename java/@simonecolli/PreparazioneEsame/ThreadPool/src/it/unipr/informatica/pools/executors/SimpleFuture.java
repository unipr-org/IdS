package it.unipr.informatica.pools.executors;

public class SimpleFuture<RetType> implements Future<RetType>{
	
	private Object mutex_;
	private RetType value_;
	private boolean done_;
	private Throwable throwable_;
	
	public SimpleFuture() {
		mutex_ = new Object();
		value_ = null;
		done_ = false;
		throwable_ = null;
	}
	
	@Override
	public RetType get() throws InterruptedException, Throwable {
		synchronized (mutex_) {
			while(!done_)
				mutex_.wait();
			
			if(throwable_ != null)
				throw throwable_;
			
			return value_;
		}
	}
	public void setValue(RetType value) {
		synchronized (mutex_) {
			if(done_)
				throw new IllegalMonitorStateException("done_ == true");
			
			value_ = value;
			done_ = true;
			mutex_.notifyAll();
		}
		
	}

	public void setThrowable(Throwable throwable) {
		if(throwable == null)
			throw new IllegalArgumentException("throwable == null");
		
		synchronized (mutex_) {
			if(done_)
				throw new IllegalMonitorStateException("done_ == true");
			
			throwable_ = throwable;
			done_ = true;
			mutex_.notifyAll();
		}
	}

	@Override
	public boolean isDone() {
		synchronized (mutex_) {
			return done_;
		}
	}

}
