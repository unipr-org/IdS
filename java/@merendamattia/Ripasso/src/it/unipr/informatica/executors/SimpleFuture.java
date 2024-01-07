package it.unipr.informatica.executors;

import it.unipr.informatica.executors.model.Future;

public class SimpleFuture<T> implements Future<T> {

	private Object mutex;
	private T value;
	private boolean done;
	private Throwable throwable;
	
	public SimpleFuture() {
		this.mutex = new Object();
		this.value = null;
		this.done = false;
		this.throwable = null;
	}
	
	@Override
	public T get() throws InterruptedException, Throwable {
		synchronized (mutex) {
			while(!done)
				mutex.wait();
			
			if(throwable != null)
				throw throwable;
			
			return value;
		}
	}

	@Override
	public boolean isDone() {
		synchronized (mutex) {
			return done;
		}
	}
	
	public void setValue(T value) {
		synchronized (mutex) {
			if(done)
				throw new IllegalMonitorStateException("done == true");
			this.value = value;
			
			this.done = true;
			mutex.notifyAll();
		}
	}
	
	public void setException(Throwable t) {
		if(t == null) 
			throw new IllegalMonitorStateException("t == null");
		
		synchronized (mutex) {
			if(done)
				throw new IllegalMonitorStateException("done == true");
			this.throwable = t;
			
			this.done = true;
			mutex.notifyAll();
		}
	}
}
