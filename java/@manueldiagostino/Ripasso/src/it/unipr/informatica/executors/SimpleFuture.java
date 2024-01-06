package it.unipr.informatica.executors;

public class SimpleFuture<RetType> implements Future<RetType> {
	private Object mutex_;
	private RetType value_;
	private Throwable throwable_;
	private boolean done_;

	public SimpleFuture() {
		mutex_ = new Object();
		value_ = null;
		throwable_ = null;
		done_ = false;
	}

	@Override
	public RetType get() throws InterruptedException, Throwable {
		synchronized (mutex_) {
			while (!done_)
				mutex_.wait();

			if (throwable_ != null)
				throw throwable_;

			return value_;
		}
	}

	@Override
	public boolean isDone() {
		synchronized (mutex_) {
			return done_;
		}
	}

	public void setValue(RetType value) {
		synchronized (mutex_) {
			if (done_)
				throw new IllegalMonitorStateException("done == true");

			value_ = value;
			done_ = true;

			mutex_.notifyAll();
		}
	}

	public void setException(Throwable throwable) {
		if (throwable == null)
			throw new IllegalMonitorStateException("throwable == null");

		synchronized (mutex_) {
			if (done_)
				throw new IllegalMonitorStateException("done == true");

			throwable_ = throwable;
			done_ = true;

			mutex_.notifyAll();
		}
	}
}
