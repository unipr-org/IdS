package it.unipr.informatica.esercizi.esercizio_decoupled;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class FutureImpl implements Future {
	private boolean isDone;
	private Object value;
	private Exception exception;
	
	public FutureImpl() {
		isDone = false;
	}

	@Override
	public void setValue(Object value) {
		synchronized (this) {
			if (isDone)
				throw new IllegalStateException("isDone already true");
			
			this.value = value;
			isDone = true;
			
			notify();
		}
	}

	@Override
	public Object getValue() throws InterruptedException {
		synchronized (this) {
			if (!isDone)
				wait();
			
			return value;
		}
	}
	

	@Override
	public void setException(Exception exception) {
		synchronized (this) {
			if (isDone)
				throw new IllegalStateException("isDone already true");
			
			isDone = true;
			this.exception = exception;
			
			notify();
		}
	}
	
	@Override
	public boolean isDone() {
		synchronized (this) {
			return isDone;
		}
	}

}
