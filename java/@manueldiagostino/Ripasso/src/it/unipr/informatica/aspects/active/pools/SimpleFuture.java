package it.unipr.informatica.aspects.active.pools;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class SimpleFuture<RetType> implements Future<RetType> {
	private RetType value;
	private Throwable exception;
	private boolean done;
	private Object mutex;
	
	public SimpleFuture() {
		this.mutex = new Object();
		this.done = false;
		this.exception = null;
		this.value = null;
	}
	
	public void setValue(RetType value) {
		synchronized (mutex) {
			this.value = value;
			done = true;
			mutex.notifyAll();
		}
	}
	
	public void setException(Throwable exception) {
		synchronized (mutex) {
			this.exception = exception;
			done = true;
			mutex.notifyAll();
		}
	}
	
	@Override
	public RetType get() throws Throwable {	
		synchronized (mutex) {
			while (!done)
				mutex.wait();
			
			if (exception != null)
				throw exception;
			
			return value;
		}
	}

}
