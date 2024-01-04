package it.unipr.informatica.exercises.esercizio_decoupled;

public class SimpleFuture implements Future {
	private Object result;
	private Object mutex;
	private Throwable exception;
	private boolean isDone;
	
	public SimpleFuture() {
		this.result = null;
		this.mutex = new Object();
		this.exception = null;
		this.isDone = false;
	}
	
	@Override
	public Object get() throws InterruptedException, Throwable {
		synchronized (mutex) {
			while(!isDone)
				mutex.wait();
			
			if(exception != null)
				throw exception;
			
			return result;
		}
	}

	@Override
	public boolean isDone() {
		synchronized (mutex) {
			return isDone;
		}
	}

	public void setValue(Object result) {
		synchronized (mutex) {
			if(isDone)
				throw new IllegalArgumentException("Result already setted");
				
			this.result = result;
			this.isDone = true;
			mutex.notifyAll();
		}
	}
	
	public void setException(Throwable exception) {
		synchronized (mutex) {
			if(isDone)
				throw new IllegalArgumentException("Exception already setted");
				
			this.exception = exception;
			this.isDone = true;
			mutex.notifyAll();
		}
	}
} // ! Future
