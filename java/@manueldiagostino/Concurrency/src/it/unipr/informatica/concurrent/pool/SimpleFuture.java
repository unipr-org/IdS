package it.unipr.informatica.concurrent.pool;

import java.util.concurrent.ExecutionException;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class SimpleFuture<ValueType> implements Future<ValueType> {
	private Object mutex_;
	// Può ritornare un valore oppure un'eccezione generata durante l'esecuzione
	private ValueType value_;
	private Throwable exception_;
	private boolean done_;
	
	public SimpleFuture() {
		mutex_ = new Object();
		value_ = null;
		exception_ = null;
		done_ = false;
	}
	
	@Override
	public ValueType get() throws InterruptedException, ExecutionException {
		synchronized (mutex_) {
			while (!done_)
				mutex_.wait();
			
			if (exception_ != null)
				throw new ExecutionException(exception_);
			
			return value_;
		}
	}

	@Override
	public void setValue(ValueType value) throws IllegalStateException {
		synchronized (mutex_) {
			if (done_)
				throw new IllegalStateException("done_ == true");

			value_ = value;
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

	@Override
	public void setException(Throwable exception) {
		if (exception == null)
			throw new IllegalArgumentException("exception == null");
		
		synchronized (mutex_) {
			if (done_)
				throw new IllegalStateException("done == true");
			
			exception_ = exception;
			done_ = true;
			
			mutex_.notifyAll();
		}
	}

}
